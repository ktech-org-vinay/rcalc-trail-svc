package com.ktech.trail.core.model.entities;

import com.ktech.trail.core.model.values.Isni;
import com.ktech.trail.core.model.values.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArtistTest {
  @Test
  void givenValidNameAndIsni_whenCreatingArtist_shouldCreate() {
    Artist artist = new Artist(new Name("Apple", "Inc"), new Isni("US0378331005"));

    Assertions.assertThat(artist).isNotNull();
  }
}
