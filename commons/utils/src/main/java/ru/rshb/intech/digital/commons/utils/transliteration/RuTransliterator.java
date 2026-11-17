package ru.rshb.intech.digital.commons.utils.transliteration;

import jakarta.annotation.Nonnull;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class RuTransliterator implements Transliterator {

  private final Map<Character, String> vocabulary = new HashMap<>() {
    {
      put('а', "a");
      put('б', "b");
      put('в', "v");
      put('г', "g");
      put('д', "d");
      put('е', "e");
      put('ж', "zh");
      put('з', "z");
      put('и', "i");
      put('й', "j");
      put('к', "k");
      put('л', "l");
      put('м', "m");
      put('н', "n");
      put('о', "o");
      put('п', "p");
      put('р', "r");
      put('с', "s");
      put('т', "t");
      put('у', "u");
      put('ф', "f");
      put('х', "x");
      put('ц', "c");
      put('ч', "ch");
      put('ш', "sh");
      put('щ', "shh");
      put('ы', "y");
      put('э', "e");
      put('ю', "yu");
      put('я', "ya");
      put('ь', "");
      put('ъ', "");
      put(' ', "-");
      put('.', "");
      put(',', "");
      put('?', "");
      put('!', "");
      put('*', "");
      put('$', "");
      put('@', "");
      put('%', "");
    }
  };

  @Nonnull
  @Override
  public String transliterate(@Nonnull String input) {

    if (StringUtils.isBlank(input)) {
      return StringUtils.EMPTY;
    }

    final var sb = new StringBuilder();
    final var lowered = input.trim().toLowerCase();

    for (int i = 0; i < lowered.length(); i++) {
      final var inCh = lowered.charAt(i);
      final var outCh = vocabulary.get(inCh);
      if (outCh != null) {
        sb.append(outCh);
        continue;
      }
      sb.append(inCh);
    }
    return sb.toString();
  }
}
