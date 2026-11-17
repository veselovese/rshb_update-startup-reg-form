package ru.rshb.intech.digital.censor.api;

import jakarta.annotation.Nonnull;

public interface CensorService {

  @Nonnull
  CensorResult validate(@Nonnull String text);
}
