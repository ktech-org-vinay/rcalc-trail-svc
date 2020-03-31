package com.ktech.trail.api.controllers;

import com.ktech.trail.api.dto.ArtistContract;
import com.ktech.trail.api.services.ArtistService;
import com.ktech.trail.core.model.entities.Artist;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController {
  @Autowired
  private ArtistService artistService;

  @GetMapping(value = "/artists", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
  public List<Artist> listAllArtists() {
    return new ArrayList<>();
  }

  @PostMapping(
      value = "/artists",
      consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
      produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
  public void saveArtist(@RequestBody ArtistContract artist) throws IOException {
    artistService.save(artist);
  }
}
