package ru.rshb.intech.digital.commons.utils.datetime;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.commons.utils.Locales;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@RequiredArgsConstructor
public enum DateTimeFormat {

  /**
   * Дата в формате "dd.MM.yyyy".
   */
  SIMPLE_DATE(
      DateTimePatterns.SIMPLE_DATE,
      DateTimeFormatter.ofPattern(DateTimePatterns.SIMPLE_DATE)
  ),

  /**
   * Дата и время в формате "dd.MM.yyyy HH:mm:ss".
   */
  SIMPLE_DATE_TIME(
      DateTimePatterns.SIMPLE_DATE_TIME,
      DateTimeFormatter.ofPattern(DateTimePatterns.SIMPLE_DATE_TIME)
  ),

  /**
   * Дата в формате "dd MMMM yyyy" c локалью RU.
   */
  FULL_DATE(
      DateTimePatterns.FULL_DATE,
      DateTimeFormatter.ofPattern(DateTimePatterns.FULL_DATE, Locales.RU)
  ),

  /**
   * Дата в формате ISO extended вида "yyyy-MM-dd".
   */
  ISO_EXTENDED_DATE(
      DateTimePatterns.ISO_EXTENDED_DATE,
      DateTimeFormatter.ofPattern(DateTimePatterns.ISO_EXTENDED_DATE)
  );

  private final String pattern;
  private final DateTimeFormatter formatter;

  @Nonnull
  public String format(@Nonnull LocalDateTime dateTime) {
    return getFormatter().format(dateTime);
  }

  @Nonnull
  public String format(@Nonnull ZonedDateTime dateTime) {
    return getFormatter().format(dateTime);
  }

  @Nonnull
  public String format(@Nonnull LocalDate date) {
    return getFormatter().format(date);
  }
}
