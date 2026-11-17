package ru.rshb.intech.digital.censor.dictionary.validator;

import ru.rshb.intech.digital.censor.api.CensorResult;
import ru.rshb.intech.digital.censor.dictionary.model.Dictionary;

import jakarta.annotation.Nonnull;

public interface DictionaryCensorValidator {

  @Nonnull
  CensorResult validate(@Nonnull String value, @Nonnull Dictionary dictionary);

}
