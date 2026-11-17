package ru.rshb.intech.digital.censor.dictionary;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.censor.api.CensorResult;
import ru.rshb.intech.digital.censor.api.CensorService;
import ru.rshb.intech.digital.censor.dictionary.provider.DictionaryProvider;
import ru.rshb.intech.digital.censor.dictionary.validator.DictionaryCensorValidator;

@Component
@RequiredArgsConstructor
public class DictionaryCensorService implements CensorService {

  private final DictionaryCensorValidator censorStrategy;
  private final DictionaryProvider dictionaryProvider;


  @Nonnull
  @Override
  public CensorResult validate(@Nonnull String text) {
    final var dictionary = dictionaryProvider.provide();
    return censorStrategy.validate(text, dictionary);
  }
}
