package ru.rshb.intech.digital.commons.validation.vk;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static org.apache.commons.lang3.StringUtils.startsWithAny;
import static ru.rshb.intech.digital.commons.validation.url.UrlUtils.isValidUrl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VkUrlUtils {

  public static final String VK_COM = "https://vk.com";
  public static final String VK_RU = "https://vk.ru";

  public static final int MAX_LENGTH = 2048;


  public static boolean isValidVkUrl(@Nonnull String value) {
    final var trimmedUrl = value.trim().toLowerCase();
    return startsWithAny(trimmedUrl, VK_COM, VK_RU) && isValidUrl(trimmedUrl);
  }
}
