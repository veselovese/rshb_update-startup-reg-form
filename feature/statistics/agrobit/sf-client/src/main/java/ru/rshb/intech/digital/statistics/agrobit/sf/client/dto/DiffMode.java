package ru.rshb.intech.digital.statistics.agrobit.sf.client.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import jakarta.annotation.Nullable;

@Getter
@RequiredArgsConstructor
public enum DiffMode {

  MONTH_OVER_MONTH("MONTH"),

  YEAR_OVER_YEAR("YEAR");

  private final String code;

  @Nullable
  public static DiffMode parse(@Nullable String code) {

    if (code == null) {
      return null;
    }

    return switch (code.toUpperCase()) {
      case "MONTH" -> MONTH_OVER_MONTH;
      case "YEAR" -> YEAR_OVER_YEAR;
      default -> null;
    };
  }
}
