package com.ktech.trail.core.ports.repositories;

import com.ktech.trail.core.model.entities.Artist;
import java.io.IOException;

public interface ArtistWriter {
  void saveArtist(Artist artist) throws IOException;
}
