package ru.rshb.intech.digital.feature.internship.core.service.statistics.registration;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Locales;
import ru.rshb.intech.digital.commons.utils.datetime.DateTimeUtilz;
import ru.rshb.intech.digital.feature.internship.core.dto.statistics.InternshipStatisticsDto;
import ru.rshb.intech.digital.feature.internship.core.dto.statistics.RegistrationCountByMonthDto;
import ru.rshb.intech.digital.feature.internship.core.dto.statistics.RegistrationCountByYearDto;
import ru.rshb.intech.digital.feature.internship.core.dto.statistics.RegistrationCountDto;
import ru.rshb.intech.digital.feature.internship.core.entity.RegistrationMonthlyStats;
import ru.rshb.intech.digital.feature.internship.core.repository.registration.RegistrationRepository;
import ru.rshb.intech.digital.feature.internship.core.service.fields.InternshipFieldService;
import ru.rshb.intech.digital.messages.I18n;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
@RequiredArgsConstructor
public class RegistrationMonthlyRelativeStatisticsCollector {

  private static final String ALL_FIELDS_PLOT_NAME = "internship.registration.fields.all";

  private final RegistrationRepository registrationRepository;
  private final InternshipFieldService fieldService;

  @Nonnull
  public List<RegistrationMonthlyStats> collectForCaching() {

    final var fields = fieldService.findAvailableFieldTitles();
    final var sinceYear = LocalDate.now().getYear() - 1;

    final var stats = new ArrayList<RegistrationMonthlyStats>();

    for (final var field : fields) {
      final var statsByField = collectAndCalcAsList(field, sinceYear);
      stats.addAll(statsByField);
    }
    stats.addAll(collectAndCalcAsList(null, sinceYear)); // по всем направлениям

    return stats;
  }

  @Nonnull
  private RegistrationMonthlyStats mapMonthData(@Nonnull MonthData monthData) {
    final var stats = new RegistrationMonthlyStats();
    stats.setDate(
        DateTimeUtilz.firstDateTimeOfMonth(monthData.getYear(), monthData.getMonth())
    );
    stats.setField(monthData.getField());
    stats.setAmount(monthData.getAmount());
    stats.setDiff(monthData.getDifference());
    return stats;
  }

  /**
   * Получить данные статистики напрямую из исходных данных.
   *
   * @param fields Направления стажировки. Должны быть предварительно провалидированы.
   * @return Данные статистики.
   */
  @Nonnull
  public InternshipStatisticsDto collectDirectly(@Nonnull List<String> fields) {
    return statsForAdjacentMonths(fields);
  }

  @Nonnull
  public InternshipStatisticsDto collect(@Nonnull Set<String> fields) {
    return statsForAdjacentMonths(new ArrayList<>(fields));
  }

  @Nonnull
  private InternshipStatisticsDto statsForAdjacentMonths(@Nonnull List<String> fields) {

    final var sinceYear = LocalDate.now().getYear() - 1;
    final var mapSeries = new HashMap<String, RegistrationCountDto>();

    fields.forEach(field -> collectAndCalc(field, sinceYear, mapSeries));
    collectAndCalc(null, sinceYear, mapSeries); // Данные по всем направлениям.

    final var plots = mapSeries.values().stream().toList();
    return new InternshipStatisticsDto(plots);
  }

  @Nonnull
  private Map<Integer, List<MonthData>> collectAndCalc(@Nullable String field, int sinceYear) {
    final var countsData = collectCountsPerMonth(sinceYear, field);
    return calcDiffPerMonth(countsData);
  }

  @Nonnull
  private List<RegistrationMonthlyStats> collectAndCalcAsList(@Nullable String field, int sinceYear) {
    final var countsData = collectCountsPerMonth(sinceYear, field);
    return calcDiffPerMonth(countsData).values()
        .stream()
        .flatMap(sList -> sList.stream())
        .map(this::mapMonthData)
        .toList();
  }

  @Nonnull
  private String allFieldsPlotName() {
    return I18n.of(ALL_FIELDS_PLOT_NAME);
  }

  /**
   * Выбирает данные из БД и вычисляет разницу по месяцам, начиная с заданного года.
   *
   * @param field Направление стажировки. Если <code>null</code> - выбирать данные по всем категориям.
   * @param sinceYear Начальный год.
   * @param plotAcc Аккумулятор выходных данных.
   */
  private void collectAndCalc(@Nullable String field, int sinceYear, @Nonnull HashMap<String, RegistrationCountDto> plotAcc) {

    final var calculated = collectAndCalc(field, sinceYear);

    final var dataByYears = calculated.entrySet()
        .stream()
        .map(entry -> dataByYear(entry.getKey(), entry.getValue()))
        .toList();

    final var fieldName = isNotBlank(field) ? field : allFieldsPlotName();

    plotAcc.put(field, new RegistrationCountDto(fieldName, dataByYears));
  }

  @Nonnull
  private Map<Integer, List<MonthData>> collectCountsPerMonth(int sinceYear, @Nullable String field) {

    final var startDate = DateTimeUtilz.firstDateTimeOfYear(sinceYear);
    final var endDate = LocalDateTime.now();
    final var preMonthDate = startDate.minusMonths(1);

    final var counts = StringUtils.isNotBlank(field)
        ? registrationRepository.findCountPerMonth(preMonthDate, endDate, field)
        : registrationRepository.findCountPerMonth(preMonthDate, endDate);

    final var resultsByYear = new HashMap<Integer, List<MonthData>>();

    for (final var monthData : counts) {
      final var fieldName = isNotBlank(field) ? field : allFieldsPlotName();
      final int monthYear = monthData.getMonth().getYear();
      final var monthsList = resultsByYear.getOrDefault(monthYear, newMonthsList(monthYear, fieldName));

      monthsList.get(monthData.getMonth().getMonth().getValue() - 1)
          .setAmount(monthData.getCount());

      resultsByYear.put(monthData.getMonth().getYear(), monthsList);
    }

    return resultsByYear;
  }

  /**
   * Возвращает коллекцию из 12 элементов (по каждому месяцу в году), заполненную ее данными по месяцам по-умолчанию.
   *
   * @param year Год.
   * @param field Направление стажировки.
   * @return Коллекция из 12 элементов данных по месяцам.
   */
  @Nonnull
  private List<MonthData> newMonthsList(int year, @Nonnull String field) {
    final var monthList = new ArrayList<MonthData>();
    for (int i = 1; i <= 12; i++) {
      monthList.add(new MonthData(year, i, field));
    }
    return monthList;
  }

  /**
   * Последовательно обходит каждую смежную пару месяцев и вычисляет относительное приращение.
   *
   * @param monthCounts Map с данными по годам.
   * @return Map с данными по годам.
   */
  @Nonnull
  private Map<Integer, List<MonthData>> calcDiffPerMonth(@Nonnull Map<Integer, List<MonthData>> monthCounts) {

    final var monthSeq = monthCounts.entrySet()
        .stream()
        .flatMap(mc -> mc.getValue().stream())
        .toList();

    for (int i = 0; i < monthSeq.size(); i++) {
      final var isNotLastIteration = (i < (monthSeq.size() - 1));
      final var m1 = monthSeq.get(i);
      final var m2 = isNotLastIteration
          ? monthSeq.get(i + 1)
          : m1;
      compareAdjacentMonths(m1, m2);
    }

    return monthCounts;
  }

  @Nonnull
  private RegistrationCountByYearDto dataByYear(int year, @Nonnull List<MonthData> monthList) {

    final var plotValues = monthList.stream()
        .map(m ->
                 new RegistrationCountByMonthDto(
                     DateTimeUtilz.localizedMonthName(m.getMonth(), Locales.RU),
                     m.getMonth(),
                     m.getDifference()
                 )
        ).toList();

    return new RegistrationCountByYearDto(year, plotValues);
  }

  /**
   * Сравнивает два смежных месяца. Вычисляет разинцу и записывает в объект,
   * относящийся к текущему месяцу.
   *
   * @param m1 Предыдущий месяц.
   * @param m2 Текущий месяц.
   */
  private void compareAdjacentMonths(@Nonnull MonthData m1, @Nonnull MonthData m2) {

    if (isFutureDate(m2.getYear(), m2.getMonth())) {
      m2.setDifference(0);
      return;
    }

    if (m1.getAmount() == m2.getAmount()) {
      m2.setDifference(0);
      return;
    }

    if (m1.getAmount() == 0) {
      m2.setDifference(100);
      return;
    }

    if (m2.getAmount() == 0) {
      m2.setDifference(-100);
      return;
    }

    final var diff = ((double) m2.getAmount() / m1.getAmount()) - 1;
    final var percents = Math.round(diff * 100);
    m2.setDifference(percents);
  }

  private boolean isFutureDate(int year, int month) {
    final var providedDate = DateTimeUtilz.firstDateOfMonth(year, month);
    return providedDate.isAfter(LocalDate.now());
  }
}
