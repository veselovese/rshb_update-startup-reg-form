package ru.rshb.intech.digital.feature.contests.patent.dto;

import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Generated
public abstract class SimpleLookupValue {

  private String id;

  private String value;

  public SimpleLookupValue(@Nonnull String id, @Nonnull String value) {
    this.id = id;
    this.value = value;
  }
}
