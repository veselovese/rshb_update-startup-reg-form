package ru.rshb.intech.digital.references.cities.okato.loader;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import jakarta.annotation.Nonnull;

@Data
@RequiredArgsConstructor
public class OkatoCode {

  private final String region;
  private final String area;
  private final String villageCouncil;
  private final String ruralLocality;
  private final String section;

  @Nonnull
  public String toString() {
    return region
           + "." + area
           + "." + villageCouncil
           + "." + ruralLocality
           + "." + section;
  }
}
