package ru.rshb.intech.digital.commons.utils.transliteration;

import jakarta.annotation.Nonnull;

public interface Transliterator {

  @Nonnull
  String transliterate(@Nonnull String input);

}
