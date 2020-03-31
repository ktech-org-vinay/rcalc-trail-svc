package com.ktech.trail.api.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


import com.ktech.trail.api.dto.ArtistContract;
import com.ktech.trail.core.model.entities.Artist;
import com.ktech.trail.core.model.events.ArtistRegisteredEvent;
import com.ktech.trail.core.ports.repositories.ArtistSavedEventPublisher;
import com.ktech.trail.core.ports.repositories.ArtistWriter;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {
  @Mock
  ArtistWriter artistWriter;

  @Mock
  ArtistSavedEventPublisher artistSavedEventPublisher;

  @InjectMocks
  ArtistService artistService;

  @Test
  public void givenArtistWriter_whenSavingArtist_shouldSave() throws IOException {
    ArtistContract artistContract = new ArtistContract("Apple", "Inc.", "US0378331005");

    artistService.save(artistContract);

    verify(artistWriter).saveArtist(any(Artist.class));
    verify(artistSavedEventPublisher).send((any(ArtistRegisteredEvent.class)));
  }
}
