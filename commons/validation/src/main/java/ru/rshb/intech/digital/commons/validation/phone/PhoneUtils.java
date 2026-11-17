package ru.rshb.intech.digital.commons.validation.phone;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isBlank;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PhoneUtils {

  public static final Pattern PHONE_PT = Pattern.compile("^\\+?\\d{1,2}\\s*\\(?\\d{2,3}\\)?\\s*\\d{3}\\-?\\d{2}\\-?\\d{2}");
  public static final String PHONE_FORMAT_SYMBOLS_PATTERN = "[\\(\\)\\s\\-]";


  @Nonnull
  public static Optional<String> getPhone(@Nullable String value) {

    if (isBlank(value)) {
      return Optional.empty();
    }

    final String trimmed = value.trim();

    if (PHONE_PT.matcher(trimmed).matches()) {
      final var normalPhone = trimmed.replaceAll(PHONE_FORMAT_SYMBOLS_PATTERN, StringUtils.EMPTY);
      return Optional.of(normalPhone);
    }

    return Optional.empty();
  }
}
