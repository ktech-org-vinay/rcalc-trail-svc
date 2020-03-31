package com.ktech.trail.api.configurations;

import com.ktech.trail.api.services.ArtistService;
import com.ktech.trail.core.ports.repositories.ArtistSavedEventPublisher;
import com.ktech.trail.core.ports.repositories.ArtistWriter;
import com.ktech.trail.couchdb.CouchDbArtistWriter;
import com.ktech.trail.kafka.KafkaAdapterConfig;
import org.ektorp.CouchDbConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(KafkaAdapterConfig.class)
public class DefaultAppConfig {


  @Bean
  public ArtistService artistService(ArtistWriter artistWriter, ArtistSavedEventPublisher artistSavedEventPublisher) {
    return new ArtistService(artistWriter, artistSavedEventPublisher);
  }

  @Bean
  public ArtistWriter artistWriter(CouchDbConnector couchDbConnector) {
    return new CouchDbArtistWriter(couchDbConnector);
  }


}
