package ru.rshb.intech.digital.feature.internship.core.mapper.statistics;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Locales;
import ru.rshb.intech.digital.feature.internship.core.dto.statistics.InternshipStatisticsDto;
import ru.rshb.intech.digital.feature.internship.core.dto.statistics.RegistrationCountByMonthDto;
import ru.rshb.intech.digital.feature.internship.core.dto.statistics.RegistrationCountByYearDto;
import ru.rshb.intech.digital.feature.internship.core.dto.statistics.RegistrationCountDto;
import ru.rshb.intech.digital.feature.internship.core.entity.RegistrationMonthlyStats;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.capitalize;

@Component
public class RegistrationMonthlyStatsMapper {

  @Nonnull
  public InternshipStatisticsDto map(@Nonnull List<RegistrationMonthlyStats> stats) {

    final var statsByField = separateStatsByField(stats);
    final var results = new ArrayList<RegistrationCountDto>();

    for (final var stat : statsByField.entrySet()) {

      final var field = stat.getKey();
      final var fieldStats = stat.getValue();

      results.add(new RegistrationCountDto(field, separateStatsByYear(fieldStats)));
    }

    return new InternshipStatisticsDto(results);
  }

  @Nonnull
  private Map<String, List<RegistrationMonthlyStats>> separateStatsByField(@Nonnull List<RegistrationMonthlyStats> stats) {
    final var result = new HashMap<String, List<RegistrationMonthlyStats>>();
    for (final var stat : stats) {
      final var statList = result.getOrDefault(stat.getField(), new ArrayList<>());
      statList.add(stat);
      result.put(stat.getField(), statList);
    }
    return result;
  }

  private List<RegistrationCountByYearDto> separateStatsByYear(@Nonnull List<RegistrationMonthlyStats> stats) {

    final var statsByYear = new HashMap<Integer, List<RegistrationMonthlyStats>>();
    for (final var stat : stats) {
      final var year = stat.getDate().getYear();
      final var yearStats = statsByYear.getOrDefault(year, new ArrayList<>());
      yearStats.add(stat);
      statsByYear.put(year, yearStats);
    }

    final var statsByYearList = new ArrayList<RegistrationCountByYearDto>();
    for (final var entry : statsByYear.entrySet()) {
      final var year = entry.getKey();
      final var yearStats = entry.getValue();
      statsByYearList.add(new RegistrationCountByYearDto(year, mapMonthCounts(yearStats)));
    }

    return statsByYearList;
  }

  @Nonnull
  private List<RegistrationCountByMonthDto> mapMonthCounts(@Nonnull List<RegistrationMonthlyStats> monthlyStats) {
    return monthlyStats.stream()
        .map(stat ->
                 new RegistrationCountByMonthDto(
                     localizedMonth(stat.getDate().getMonthValue()),
                     stat.getDate().getMonthValue(),
                     stat.getDiff()
                 )
        ).toList();
  }

  @Nonnull
  private String localizedMonth(int number) {
    final var name = Month.of(number)
        .getDisplayName(TextStyle.FULL_STANDALONE, Locales.RU);
    return capitalize(name);
  }

}
