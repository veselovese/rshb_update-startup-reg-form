package ru.rshb.intech.digital.references.cities.okato.loader;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OkatoItem {

  private OkatoItem parent;
  private OkatoCode code;
  private String title;
  private String administrativeCenter;

}
