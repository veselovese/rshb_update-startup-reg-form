package ru.rshb.intech.digital.commons.forms.attributes;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

@FunctionalInterface
public interface PreCheckAnalyzer<DV> {

  @Nonnull
  PreCheckResult analyze(@Nullable DV value);
}
