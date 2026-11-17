package ru.rshb.intech.digital.commons.validation.url;


import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlUtils {

  public static boolean isValidUrl(@Nonnull String value) {
    try {
      URI.create(value).toURL();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Nonnull
  public static Optional<URI> createUriIfValid(@Nonnull String urlStr) {
    try {
      return Optional.of(
          URI.create(urlStr)
      );
    } catch (Exception e) {
      return Optional.empty();
    }
  }
}
