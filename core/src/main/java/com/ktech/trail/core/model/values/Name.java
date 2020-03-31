package com.ktech.trail.core.model.values;

import lombok.Value;

@Value
public class Name {
  String givenName;
  String surname;

  public Name(String givenName, String surname) {
    validateGivenName(givenName);
    this.givenName = givenName;
    this.surname = surname;
  }

  private void validateGivenName(String givenName) {
    if (givenName == null) {
      throw new IllegalArgumentException("Empty given name");
    }
  }

  @Override
  public String toString() {
    return givenName + " " + surname;
  }
}
