package com.ktech.trail.kafka;

import com.ktech.trail.core.model.events.ArtistRegisteredEvent;
import com.ktech.trail.core.ports.repositories.ArtistSavedEventPublisher;
import com.ktech.trail.kafka.events.avro.ArtistSavedEvent;
import com.ktech.trail.kafka.events.avro.Isni;
import com.ktech.trail.kafka.events.avro.Name;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
public class DefaultArtistSavedEventPublisher implements ArtistSavedEventPublisher {

  private final KafkaTemplate<String, ArtistSavedEvent> kafkaTemplate;
  private KafkaAdapterProperties props;

  public DefaultArtistSavedEventPublisher(
      KafkaTemplate<String, ArtistSavedEvent> kafkaTemplate, KafkaAdapterProperties props) {
    this.kafkaTemplate = kafkaTemplate;
    this.props = props;
  }

  public void sendAsync(ArtistSavedEvent artistSavedEvent) {
    kafkaTemplate
        .send(new GenericMessage<>(artistSavedEvent))
        .addCallback(
            result ->
                log.info(
                    String.format(
                        "Save Event for Artist with isin {%s} was successful !",
                        result.getProducerRecord().value().getIsni())),
            ex ->
                log.error(
                    String.format(
                        "Save Event for Artist with failed with exception {%s}  !",
                        ex.getMessage())));
  }

  @SneakyThrows
  @Override
  public void send(ArtistRegisteredEvent artistRegisteredEvent) {
    ArtistSavedEvent artistSavedEvent =
        ArtistSavedEvent.newBuilder()
            .setName(
                Name.newBuilder()
                    .setGivenName(artistRegisteredEvent.getName().getGivenName())
                    .setSurName(artistRegisteredEvent.getName().getSurname())
                    .build())
            .setIsni(Isni.newBuilder().setCode(artistRegisteredEvent.getIsni().getCode()).build())
            .build();
    ListenableFuture<SendResult<String, ArtistSavedEvent>> future =
        kafkaTemplate.send(new GenericMessage<>(artistSavedEvent));
    SendResult<String, ArtistSavedEvent> result =
        future.get(props.getPublisher().getSyncSendTimeoutms(), TimeUnit.MILLISECONDS);
    log.info(
        String.format(
            "Save Event for Artist with isin {%s} was successful !",
            result.getProducerRecord().value().getIsni()));
  }
}
