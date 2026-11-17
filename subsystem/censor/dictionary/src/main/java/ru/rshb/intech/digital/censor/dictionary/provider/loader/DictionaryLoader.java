package ru.rshb.intech.digital.censor.dictionary.provider.loader;

import ru.rshb.intech.digital.censor.dictionary.model.Dictionary;

import jakarta.annotation.Nonnull;

public interface DictionaryLoader {

  @Nonnull
  Dictionary load();

}
