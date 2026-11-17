package ru.rshb.intech.digital.commons.utils.datetime;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.commons.utils.Locales;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static ru.rshb.intech.digital.commons.utils.Preconditions.checkArgument;
import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateTimeUtilz {

  /**
   * Возвращает первый день (и время) указанного года.
   *
   * @param year Год.
   * @return Дата и время.
   */
  @Nonnull
  public static LocalDateTime firstDateTimeOfYear(int year) {
    return LocalDateTime.of(year, Month.JANUARY, 1, 0, 0, 0);
  }

  /**
   * Возвращает первый день указанного года.
   *
   * @param year Год.
   * @return Дата.
   */
  @Nonnull
  public static LocalDate firstDateOfYear(int year) {
    return LocalDate.of(year, Month.JANUARY, 1);
  }

  /**
   * Последний день указанного года.
   *
   * @param year Год.
   * @return Дата.
   */
  @Nonnull
  public static LocalDate lastDateOfYear(int year) {
    return LocalDate.of(year, Month.DECEMBER, 31);
  }

  /**
   * Последний день текущего года.
   *
   * @return Дата.
   */
  @Nonnull
  public static LocalDate lastDateOfYear() {
    return lastDateOfYear(
        LocalDate.now().getYear()
    );
  }

  /**
   * Первый день заданного месяца.
   *
   * @param year Год
   * @param month Месяц
   * @return Дата.
   */
  @Nonnull
  public static LocalDate firstDateOfMonth(int year, int month) {
    return LocalDate.of(year, month, 1);
  }

  /**
   * Первый день заданного месяца.
   *
   * @param year Год
   * @param month Месяц
   * @return Дата и время.
   */
  @Nonnull
  public static LocalDateTime firstDateTimeOfMonth(int year, int month) {
    return LocalDateTime.of(year, month, 1, 0, 0, 0);
  }

  /**
   * Локализованное полное наименование месяца в заданной локали.
   *
   * @param monthNum Номер месяца.
   * @param locale Локаль.
   * @return Наименование месяца.
   */
  @Nonnull
  public static String localizedMonthName(int monthNum, Locale locale) {
    final var name = Month.of(monthNum)
        .getDisplayName(TextStyle.FULL_STANDALONE, locale);
    return capitalize(name);
  }

  /**
   * Локализованное полное наименование месяца в RU локали.
   *
   * @param monthNum Номер месяца.
   * @return Наименование месяца.
   */
  @Nonnull
  public static String localizedMonthName(int monthNum) {
    final var name = Month.of(monthNum)
        .getDisplayName(TextStyle.FULL_STANDALONE, Locales.RU);
    return capitalize(name);
  }

  /**
   * Возвращает true если указанная дата входит в заданный период.
   *
   * @param date Проверяемая дата.
   * @param startDate Левая граница периода (включительно).
   * @param endDate Правая граница периода (включительно).
   * @return Результат проверки.
   */
  public static boolean isDateBetween(@Nonnull LocalDate date,
                                       @Nonnull LocalDate startDate,
                                       @Nonnull LocalDate endDate) {

    return (date.isEqual(startDate) || date.isAfter(startDate))
           && (date.isEqual(endDate) || date.isBefore(endDate));
  }

  /**
   * Вовзвращает ture если указанная строка содержит дату, соответствующю
   * формату "dd.MM.yyyy".
   *
   * @param dateStr Проверяемая строка.
   * @return Результат проверки.
   */
  public static boolean isSimpleDate(@Nonnull String dateStr) {
    try {
      LocalDate.parse(dateStr.trim(), SIMPLE_DATE.getFormatter());
      return true;
    } catch (DateTimeParseException e) {
      return false;
    }
  }

  /**
   * Вовзвращает ture если указанная строка НЕ содержит дату, соответствующю
   * формату "dd.MM.yyyy".
   *
   * @param dateStr Проверяемая строка.
   * @return Результат проверки.
   */
  public static boolean isNotSimpleDate(@Nonnull String dateStr) {
    return !isSimpleDate(dateStr);
  }

  public static boolean isSinceYear(int year, int minYear) {
    final int currentYear = LocalDate.now().getYear();
    checkArgument(currentYear >= minYear, "Min year cannot be greater than current year");
    return (year >= minYear && year <= currentYear);
  }

  @Nonnull
  public static LocalDateTime dayStartTime(@Nonnull LocalDate date) {
    return LocalDateTime.of(date, LocalTime.MIN);
  }

  @Nonnull
  public static LocalDateTime dayEndTime(@Nonnull LocalDate date) {
    return LocalDateTime.of(date, LocalTime.MAX);
  }

  @Nonnull
  public static LocalDateTime todayStartTime() {
    return dayEndTime(LocalDate.now());
  }

  @Nonnull
  public static LocalDateTime todayEndTime() {
    return dayEndTime(LocalDate.now());
  }

}
