package com.ktech.trail.kafka;

import static org.assertj.core.api.Assertions.assertThatCode;

import com.ktech.trail.core.model.events.ArtistRegisteredEvent;
import com.ktech.trail.core.model.values.Isni;
import com.ktech.trail.core.model.values.Name;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {KafkaAdapterConfig.class})
@EnableAutoConfiguration
class DefaultArtistSavedEventPublisherIT {
  @Autowired
  DefaultArtistSavedEventPublisher publisher;

  @Test
  public void givenValidArtistSavedEvent_whenSendIsInvoked_shouldInvokeMessageHandler() {
    ArtistRegisteredEvent event =
        ArtistRegisteredEvent.builder()
            .name(new Name("Apple", ", Inc."))
            .isni(new Isni("98989898"))
            .build();
    assertThatCode(() -> publisher.send(event)).doesNotThrowAnyException();
  }
}
