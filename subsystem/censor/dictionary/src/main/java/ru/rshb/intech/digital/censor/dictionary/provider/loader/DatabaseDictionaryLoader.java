package ru.rshb.intech.digital.censor.dictionary.provider.loader;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.censor.dictionary.model.Dictionary;
import ru.rshb.intech.digital.censor.dictionary.model.Word;
import ru.rshb.intech.digital.censor.dictionary.repository.DictionaryRepository;

import jakarta.annotation.Nonnull;

@Primary
@Component
@RequiredArgsConstructor
public class DatabaseDictionaryLoader implements DictionaryLoader {

  private final DictionaryRepository dictionaryRepository;

  @Nonnull
  @Override
  public Dictionary load() {

    final var words = dictionaryRepository.findAllByEnabledIsTrueOrderByRootAsc()
        .stream()
        .map(entity -> new Word(entity.getRoot()))
        .toList();

    return new Dictionary(words);
  }
}
