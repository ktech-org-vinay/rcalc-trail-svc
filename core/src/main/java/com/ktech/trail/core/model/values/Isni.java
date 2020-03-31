package com.ktech.trail.core.model.values;

import lombok.Value;

@Value
public class Isni {
  String code;

  public Isni(String code) {
    validate(code);
    this.code = code;
  }

  private void validate(String code) {
    if (code == null || code.isEmpty()) {
      throw new IllegalArgumentException("Empty ISIN Code");
    }
  }

  @Override
  public String toString() {
    return code;
  }
}
