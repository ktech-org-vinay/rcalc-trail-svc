package com.ktech.trail.api.dto;

import com.ktech.trail.core.model.entities.Artist;
import com.ktech.trail.core.model.values.Isni;
import com.ktech.trail.core.model.values.Name;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArtistContract {
  private String givenName;
  private String surname;
  private String isinCode;

  public Artist toEntity() {
    return new Artist(new Name(givenName, surname), new Isni(isinCode));
  }
}
