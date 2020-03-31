package com.ktech.trail.api.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ktech.trail.api.SampleServiceApplication;
import com.ktech.trail.api.configurations.DefaultAppConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MimeTypeUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    classes = { SampleServiceApplication.class },
    webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = {"classpath:application.yml"})
@ContextConfiguration(
    classes = {DefaultAppConfig.class},
    initializers = ConfigFileApplicationContextInitializer.class)
class ArtistControllerIT {
  @Autowired
  private MockMvc mvc;

  @Test
  public void givenValidAppConfiguration_whenLoadingContext_shouldLoad() {
    assertThat(mvc).isNotNull();
  }

  @Test
  public void givenValidAppConfiguration_whenRequestingAllArtists_shouldReturnAllArtists()
      throws Exception {
    mvc.perform(get("/artists").accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());
  }

  @Test
  public void givenValidAppConfigurationAndArtist_whenPosted_shouldSaveArtist() throws Exception {
    mvc.perform(
        post("/artists")
            .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE)
            .content(
                "{\"givenName\": \"Apple\","
                    + " \"surname\": \"Inc.\", \"isinCode\": \"US0378331005\"}"))
        .andExpect(status().is2xxSuccessful());
  }
}
