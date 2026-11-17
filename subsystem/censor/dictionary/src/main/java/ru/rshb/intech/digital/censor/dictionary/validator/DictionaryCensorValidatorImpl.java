package ru.rshb.intech.digital.censor.dictionary.validator;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.censor.api.CensorResult;
import ru.rshb.intech.digital.censor.dictionary.model.Dictionary;

import jakarta.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.containsAny;
import static org.apache.commons.lang3.StringUtils.normalizeSpace;

@Component
public class DictionaryCensorValidatorImpl implements DictionaryCensorValidator {

  private final Pattern ALL_PUNCTUATION_PATTERN = Pattern.compile("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\s\\\"\\<\\>]");

  @Nonnull
  @Override
  public CensorResult validate(@Nonnull String value, @Nonnull Dictionary dictionary) {

    final var splitted = normalizeSpace(value)
        .toLowerCase()
        .split(StringUtils.SPACE);

    final var words = Arrays.stream(splitted)
        .map(word -> removePunctuation(word))
        .filter(StringUtils::isNotBlank)
        .toList();

    if (isExplicitViolation(words, dictionary)) {
      return CensorResult.VIOLATION;
    }

    return CensorResult.OK;
  }

  private boolean isExplicitViolation(@Nonnull List<String> words, @Nonnull Dictionary dictionary) {

    final var dic = dictionary.getWords()
        .stream()
        .map(word -> word.getText())
        .toList()
        .toArray(new String[]{});

    return words.stream()
        .anyMatch(word -> containsAny(word, dic));
  }

  @Nonnull
  private String removePunctuation(@Nonnull String word) {
    return RegExUtils.removeAll(word, ALL_PUNCTUATION_PATTERN);
  }
}
