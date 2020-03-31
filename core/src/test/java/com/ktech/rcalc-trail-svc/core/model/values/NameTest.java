package com.ktech.trail.core.model.values;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;

public class NameTest {
  @Test
  void givenProperNames_whenCreatingName_shouldCreate() {
    Name name = new Name("Siddhartha", "Ghosh");

    assertThat(name).isNotNull().isInstanceOf(Name.class);
  }

  @Test
  void givenOnlySurname_whenCreatingName_shouldFail() {
    Throwable throwable = catchThrowable(() -> new Name(null, "Ghosh"));

    assertThat(throwable).isNotNull().isInstanceOf(IllegalArgumentException.class);
  }
}
