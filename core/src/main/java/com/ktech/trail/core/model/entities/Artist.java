package com.ktech.trail.core.model.entities;

import com.ktech.trail.core.model.values.Isni;
import com.ktech.trail.core.model.values.Name;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Artist {
  Name name;
  final Isni isni;
}
