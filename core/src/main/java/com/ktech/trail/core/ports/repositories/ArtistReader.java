package com.ktech.trail.core.ports.repositories;

import com.ktech.trail.core.model.entities.Artist;
import com.ktech.trail.core.model.values.Isni;
import java.io.IOException;

public interface ArtistReader {
  Artist findArtistByIdentifier(Isni isni) throws IOException;
}
