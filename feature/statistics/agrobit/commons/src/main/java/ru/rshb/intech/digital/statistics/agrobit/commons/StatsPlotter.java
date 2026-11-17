package ru.rshb.intech.digital.statistics.agrobit.commons;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.utils.Locales;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.capitalize;

public class StatsPlotter {

  @Nonnull
  public PlotBundleDto groupStatsData(@Nonnull List<ValueDiffStat> stats) {

    final Map<String, List<ValueDiffStat>> groupedBySub = new HashMap<>();

    for (final var stat : stats) {

      final var sub = stat.getSubType();
      final var subStats = Optional.ofNullable(groupedBySub.get(sub))
          .orElse(new ArrayList<>());
      subStats.add(stat);

      groupedBySub.put(sub, subStats);
    }

    final var parameterDtos = new ArrayList<StatsByYearsDto>();

    for (final var subStatsEntry : groupedBySub.entrySet()) {

      final var paramName = subStatsEntry.getKey();
      final var paramStats = subStatsEntry.getValue()
          .stream()
          .sorted()
          .toList();

      parameterDtos.add(
          groupStatsByYears(paramName, paramStats)
      );
    }

    return new PlotBundleDto(parameterDtos);
  }

  @Nonnull
  private StatsByYearsDto groupStatsByYears(@Nonnull String paramName, @Nonnull List<ValueDiffStat> stats) {

    final var dto = new StatsByYearsDto();
    dto.setName(paramName);
    dto.setYears(new ArrayList<>());

    final var groupedByYear = new HashMap<Integer, List<ValueDiffStat>>();

    for (final var stat : stats) {

      final var statYear = stat.getDate().getYear();

      final var yearList = Optional.ofNullable(groupedByYear.get(statYear))
          .orElse(new ArrayList<>());

      yearList.add(stat);
      groupedByYear.put(statYear, yearList);
    }

    for (final var yearData : groupedByYear.entrySet()) {

      final var year = yearData.getKey();
      final var yearStats = yearData.getValue();

      dto.getYears()
          .add(
              groupStatsByMonths(year, yearStats)
          );
    }

    return dto;
  }

  @Nonnull
  private StatsYearDto groupStatsByMonths(int year, @Nonnull List<ValueDiffStat> stats) {

    final var dto = new StatsYearDto();
    dto.setYear(year);
    dto.setMonths(new ArrayList<>());

    final var groupedByMonth = preFillStats(year);

    for (final var stat : stats) {
      final var monthNumber = stat.getDate().getMonthValue();
      groupedByMonth.put(monthNumber, stat);
    }

    for (final var monthData : groupedByMonth.entrySet()) {

      final var monthNumber = monthData.getKey();
      final var monthStat = monthData.getValue();

      dto.getMonths()
          .add(
              new StatsMonthDto(
                  localizedMonth(monthNumber),
                  monthNumber - 1,
                  (double) monthStat.getValueDiffPct()
              )
          );
    }

    return dto;
  }

  @Nonnull
  private Map<Integer, ValueDiffStat> preFillStats(int year) {
    final var stats = new HashMap<Integer, ValueDiffStat>();
    for (int monthNumber = 1; monthNumber <= 12; monthNumber++) {
      stats.put(monthNumber, dummyStat(year, monthNumber));
    }
    return stats;
  }

  @Nonnull
  private ValueDiffStat dummyStat(int year, int monthNumber) {
    final var dummyStat = new ValueDiffStat();
    dummyStat.setDate(LocalDate.of(year, monthNumber, 1));
    dummyStat.setValue(0L);
    dummyStat.setValueDiff(0L);
    dummyStat.setValueDiffPct(0L);
    return dummyStat;
  }

  @Nonnull
  private String localizedMonth(int number) {
    final var name = Month.of(number)
        .getDisplayName(TextStyle.FULL_STANDALONE, Locales.RU);
    return capitalize(name);
  }

}
