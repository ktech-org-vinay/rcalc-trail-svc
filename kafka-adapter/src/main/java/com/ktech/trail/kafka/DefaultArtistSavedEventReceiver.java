package com.ktech.trail.kafka;

import com.ktech.trail.core.model.events.ArtistRegisteredEvent;
import com.ktech.trail.core.model.values.Isni;
import com.ktech.trail.core.model.values.Name;
import com.ktech.trail.core.ports.repositories.ArtistSavedEventReceiver;
import com.ktech.trail.kafka.events.avro.ArtistSavedEvent;
import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class DefaultArtistSavedEventReceiver {
  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }

  // Ideally a Business Component to be injected by the CORE
  private ArtistSavedEventReceiver receiver = event -> log.info("received event" + event);

  @KafkaListener(topics = "artist")
  public void receive(ArtistSavedEvent event) {

    // map message received to business event
    ArtistRegisteredEvent businessEvent = new ArtistRegisteredEvent();
    businessEvent.setIsni(new Isni(event.getIsni().getCode()));
    businessEvent.setName(new Name(event.getName().getGivenName(), event.getName().getSurName()));

    // hand the business event to core business object
    receiver.receive(businessEvent);

    latch.countDown();
  }
}
