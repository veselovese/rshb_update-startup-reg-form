package ru.rshb.intech.digital.references.cities.core.service;

import org.springframework.stereotype.Component;

import jakarta.annotation.Nonnull;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.startsWithIgnoreCase;
import static org.apache.commons.lang3.StringUtils.trim;

@Component
public class CityNameNormalizer {

  private static final String[] PREFIXES = {"г ", "гор "};


  @Nonnull
  public String normalize(String cityPartialName) {
    return cleanupPartialCityName(cityPartialName);
  }

  @Nonnull
  private String cleanupPartialCityName(@Nonnull String partialName) {
    final var trimmed = trim(partialName);
    return hasPrefix(trimmed)
        ? getLastPart(trimmed)
        : trimmed;
  }

  private boolean hasPrefix(@Nonnull String providedStr) {
    return Stream.of(PREFIXES)
        .anyMatch(prefix -> startsWithIgnoreCase(providedStr, prefix));
  }

  @Nonnull
  private String getLastPart(@Nonnull String word) {
    final var parts = word.split(SPACE);
    return (parts.length > 1)
        ? parts[parts.length - 1]
        : word;
  }
}
