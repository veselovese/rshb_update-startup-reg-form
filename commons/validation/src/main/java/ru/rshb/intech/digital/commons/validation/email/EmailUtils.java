package ru.rshb.intech.digital.commons.validation.email;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import jakarta.annotation.Nullable;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailUtils {

  public static final int EMAIL_MAX_LENGTH = 254;
  public static final Pattern EMAIL_PATTERN = Pattern.compile("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$");


  public static boolean isValidAddress(@Nullable String email) {

    if (StringUtils.isBlank(email)) {
      return false;
    }

    final var trimmed = email.trim();
    if (trimmed.length() > EMAIL_MAX_LENGTH) {
      return false;
    }

    return EMAIL_PATTERN.matcher(trimmed)
        .matches();
  }

  public static boolean isNotValidAddress(@Nullable String email) {
    return !isValidAddress(email);
  }
}
