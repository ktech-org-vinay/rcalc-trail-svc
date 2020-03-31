package com.ktech.trail.api;

import com.ktech.trail.api.controllers.ArtistController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationSmokeTest {
  @Autowired
  ArtistController artistController;

  @Test
  void contextLoads() {
    Assertions.assertThat(artistController).isNotNull();
  }
}
