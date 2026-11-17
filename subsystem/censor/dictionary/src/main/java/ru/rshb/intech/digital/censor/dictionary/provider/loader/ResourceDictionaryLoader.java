package ru.rshb.intech.digital.censor.dictionary.provider.loader;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.censor.dictionary.model.Dictionary;
import ru.rshb.intech.digital.censor.dictionary.model.Word;

import jakarta.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class ResourceDictionaryLoader implements DictionaryLoader {

  private static final String DEFAULT_FILE = "/censored.txt";

  private final String resourceName;

  public ResourceDictionaryLoader(@Nonnull String resourceName) {
    this.resourceName = resourceName;
  }

  public ResourceDictionaryLoader() {
    this(DEFAULT_FILE);
  }


  @Nonnull
  public Dictionary load() {

    try (final var is = getClass().getResourceAsStream(resourceName);
         final var reader = new BufferedReader(new InputStreamReader(is))) {

      return new Dictionary(
          reader.lines()
              .filter(StringUtils::isNotBlank)
              .map(line -> new Word(line))
              .toList()
      );

    } catch (Exception e) {
      throw new RuntimeException("Dictionary resource loading error", e);
    }
  }
}
