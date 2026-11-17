package ru.rshb.intech.digital.commons.validation.text;


import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TextValidationUtils {

  private static final String EXTRA_SPACES = "\\s{2,}";
  private static final String TABS_OR_LF = "[\\t\\n]";

  @Nullable
  public static String removeNonPrintableChars(@Nullable String input) {
    if (StringUtils.isBlank(input)) {
      return null;
    }
    return input.trim()
        .replaceAll(TABS_OR_LF, StringUtils.SPACE)
        .replaceAll(EXTRA_SPACES, StringUtils.SPACE)
        .replaceAll(StringUtils.LF, StringUtils.EMPTY)
        ;
  }

}
