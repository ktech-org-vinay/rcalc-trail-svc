package com.ktech.trail.core.model.values;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsniTest {
  public static final String VALID_ISIN = "US0378331005";

  @Test
  void givenValidCode_whenCreatingIsin_shouldCreate() {
    Isni isni = new Isni(VALID_ISIN);

    assertThat(isni).isNotNull();
  }

  @Test
  void givenEmptyCode_whenCreatingIsin_shouldFail() {
    Throwable throwable = Assertions.catchThrowable(() -> new Isni(""));

    assertThat(throwable).isNotNull().isInstanceOf(IllegalArgumentException.class);
  }
}
