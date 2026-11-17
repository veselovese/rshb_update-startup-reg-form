package ru.rshb.intech.digital.commons.validation.telegram;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.startsWithIgnoreCase;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TelegramUtils {

  public static final String TG_URL_BASE = "t.me/";
  public static final String TG_URL_FULL = "https://" + TG_URL_BASE;
  public static final String TG_USERNAME_PREFIX = "@";


  public static boolean isValidLink(String value) {

    if (isBlank(value)) {
      return false;
    }

    final var trimmed = value.trim();
    return isValidUsername(trimmed) || isValidUrl(trimmed);
  }

  public static boolean isNotValidLink(String value) {
    return !isValidLink(value);
  }

  public static boolean isValidUrl(String url) {
    return startsWithIgnoreCase(url, TG_URL_FULL)
        || startsWithIgnoreCase(url, TG_URL_BASE);
  }

  public static boolean isValidUsername(String username) {
    return startsWithIgnoreCase(username, TG_USERNAME_PREFIX);
  }
}
