package com.ktech.trail.api.services;

import com.ktech.trail.api.dto.ArtistContract;
import com.ktech.trail.core.model.entities.Artist;
import com.ktech.trail.core.model.events.ArtistRegisteredEvent;
import com.ktech.trail.core.ports.repositories.ArtistSavedEventPublisher;
import com.ktech.trail.core.ports.repositories.ArtistWriter;
import java.io.IOException;

public class ArtistService {
  private ArtistWriter artistWriter;
  private ArtistSavedEventPublisher artistSavedEventPublisher;

  public ArtistService(
      ArtistWriter artistWriter, ArtistSavedEventPublisher artistSavedEventPublisher) {
    this.artistWriter = artistWriter;
    this.artistSavedEventPublisher = artistSavedEventPublisher;
  }

  public void save(ArtistContract artistContract) throws IOException {
    Artist artist = artistContract.toEntity();
    artistWriter.saveArtist(artist);
    artistSavedEventPublisher.send(ArtistRegisteredEvent.fromEntity(artist));
  }
}
