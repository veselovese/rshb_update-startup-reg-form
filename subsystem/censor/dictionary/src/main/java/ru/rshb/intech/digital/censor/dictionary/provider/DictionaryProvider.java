package ru.rshb.intech.digital.censor.dictionary.provider;

import ru.rshb.intech.digital.censor.dictionary.model.Dictionary;

import jakarta.annotation.Nonnull;

public interface DictionaryProvider {

  @Nonnull
  Dictionary provide();

}
