package ru.rshb.intech.digital.statistics.agrobit.salary.service.sfct;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.commons.utils.Locales;
import ru.rshb.intech.digital.statistics.agrobit.commons.PlotBundleDto;
import ru.rshb.intech.digital.statistics.agrobit.commons.StatsByYearsDto;
import ru.rshb.intech.digital.statistics.agrobit.commons.StatsMonthDto;
import ru.rshb.intech.digital.statistics.agrobit.commons.StatsPlotter;
import ru.rshb.intech.digital.statistics.agrobit.commons.StatsYearDto;
import ru.rshb.intech.digital.statistics.agrobit.commons.ValueDiffStat;
import ru.rshb.intech.digital.statistics.agrobit.salary.dto.PositionCategoryImpl;
import ru.rshb.intech.digital.statistics.agrobit.salary.dto.SalaryStatsFilter;
import ru.rshb.intech.digital.statistics.agrobit.salary.entity.SalaryAvgStatsEntity;
import ru.rshb.intech.digital.statistics.agrobit.salary.entity.SalaryCategoryStatsEntity;
import ru.rshb.intech.digital.statistics.agrobit.salary.repository.SalaryAvgStatsRepository;
import ru.rshb.intech.digital.statistics.agrobit.salary.repository.SalaryCategoryStatsRepository;
import ru.rshb.intech.digital.statistics.agrobit.salary.repository.SalaryPositionCategoryRepository;
import ru.rshb.intech.digital.statistics.agrobit.salary.repository.SalaryPositionRepository;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.PositionCategory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeUtilz.firstDateOfYear;
import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeUtilz.isDateBetween;
import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeUtilz.lastDateOfYear;
import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeUtilz.localizedMonthName;

@Service
@RequiredArgsConstructor
public class SfctSalaryStatisticsServiceImpl implements SfctSalaryStatisticsService {

  private final SalaryAvgStatsRepository salaryStatsLocalRepository;
  private final SalaryPositionCategoryRepository salaryPositionCategoryRepository;
  private final SalaryPositionRepository salaryPositionRepository;
  private final SalaryCategoryStatsRepository salaryCategoryStatsRepository;
  private final StatsPlotter statsPlotter = new StatsPlotter();

  @Nonnull
  public PlotBundleDto getAvgSalaryStatistics(@Nonnull SalaryStatsFilter filter) {

    final var categoryId = filter.getCategoryId();
    final var positionIds = filter.getPositionIds();

    if (isBlank(categoryId) && positionIds.isEmpty()) {
      return getAllSalaryStats();
    }

    if (!positionIds.isEmpty()) {
      return getSalaryStatsByPositions(positionIds);
    }

    return getSalaryStatsByCategory(categoryId);
  }

  @Nonnull
  private PlotBundleDto getAllSalaryStats() {

    final var stats = salaryStatsLocalRepository.findAll()
        .stream()
        .map(stat -> mapStats(stat))
        .toList();

    return statsPlotter.groupStatsData(stats);
  }

  @Nonnull
  private ValueDiffStat mapStats(@Nonnull SalaryAvgStatsEntity stat) {
    final var value = new ValueDiffStat();
    value.setDate(stat.getReportDate());
    value.setCategory(stat.getCategory());
    value.setSubType(stat.getPosition());
    value.setValue(stat.getValue());
    value.setValueDiff(stat.getDiff());
    value.setValueDiffPct(stat.getDiffPct());
    return value;
  }

  @Nonnull
  private PlotBundleDto getSalaryStatsByPositions(@Nonnull List<String> positionIds) {

    final var positions = salaryPositionRepository.findAllByExternalIdInAndEnabledTrueOrderByNameAsc(positionIds)
        .stream()
        .map(position -> position.getName())
        .toList();

    if (positions.isEmpty()) {
      return new PlotBundleDto(List.of());
    }

    final var stats = salaryStatsLocalRepository.findAllByPositionInOrderByPosition(positions)
        .stream()
        .map(stat -> mapStats(stat))
        .toList();

    return statsPlotter.groupStatsData(stats);
  }

  @Nonnull
  private PlotBundleDto getSalaryStatsByCategory(@Nonnull String categoryId) {

    final var category = salaryPositionCategoryRepository.findByExternalIdIgnoreCaseAndEnabledTrue(categoryId)
        .map(cat -> cat.getName())
        .orElse(null);

    if (category == null) {
      return new PlotBundleDto(List.of());
    }

    final var stats = salaryStatsLocalRepository.findAllByCategoryIgnoreCase(category)
        .stream()
        .map(stat -> mapStats(stat))
        .toList();

    return statsPlotter.groupStatsData(stats);
  }

  @Nonnull
  @Override
  public PlotBundleDto getAvgSalaryStatsByCategories(@Nullable String fields) {

    final List<String> categoryNames = StringUtils.isNotBlank(fields)
        ? Arrays.asList(fields.split(","))
        : List.of();

    final var categories = new HashSet<PositionCategory>();

    if (categoryNames.isEmpty()) {
      categories.addAll(
          salaryPositionCategoryRepository.findAllByEnabledTrueOrderByNameAsc()
              .stream()
              .map(cat -> new PositionCategoryImpl(cat))
              .toList()
      );
      categories.add(PositionCategory.ALL);
    } else {
      categories.addAll(
          salaryPositionCategoryRepository.findAllByNameInAndEnabledTrue(categoryNames)
              .stream()
              .map(cat -> new PositionCategoryImpl(cat))
              .toList()
      );
    }

    return getStatsByCategories(categories);
  }

  @Nonnull
  private PlotBundleDto getStatsByCategories(@Nonnull Set<PositionCategory> categories) {

    final var dataPool = new HashMap<PositionCategory, List<SalaryCategoryStatsEntity>>();

    LocalDate oldestDate = LocalDate.now();

    for (final var category : categories) {

      final var categoryData = salaryCategoryStatsRepository.findAllByCategoryIgnoreCaseOrderByReportDate(category.getCategoryName());
      dataPool.put(category, categoryData);

      if (!categoryData.isEmpty()) {
        final var oldestRecordDate = categoryData.get(0).getReportDate();
        if (oldestRecordDate.isBefore(oldestDate)) {
          oldestDate = oldestRecordDate;
        }
      }
    }

    final var oldestStatYear = oldestDate.getYear();

    final var plots = categories.stream()
        .map(category -> collectPlot(category, dataPool.get(category), oldestStatYear))
        .toList();

    return new PlotBundleDto(plots);
  }

  @Nonnull
  private StatsByYearsDto collectPlot(@Nonnull PositionCategory category,
                                      @Nonnull List<SalaryCategoryStatsEntity> rawStats,
                                      int oldestYear) {

    final var result = new StatsByYearsDto();
    result.setName(category.getCategoryName());
    result.setYears(getPlotDataByYears(rawStats, oldestYear));

    return result;
  }

  @Nonnull
  private List<StatsYearDto> getPlotDataByYears(@Nonnull List<SalaryCategoryStatsEntity> catStats, int oldestYear) {

    if (catStats.isEmpty()) {
      return Collections.emptyList();
    }

    final var currentYear = LocalDate.now().getYear();
    final var dataByYears = new ArrayList<StatsYearDto>();

    for (int year = oldestYear; year <= currentYear; year++) {
      dataByYears.add(getPlotDataForYear(year, catStats));
    }

    return dataByYears;
  }

  @Nonnull
  private StatsYearDto getPlotDataForYear(int year, @Nonnull List<SalaryCategoryStatsEntity> catStats) {

    final var statsByMonth = new ArrayList<StatsMonthDto>();
    final var defaultValue = 0D;

    for (int monthIndex = 1; monthIndex <= 12; monthIndex++) {
      statsByMonth.add(
          new StatsMonthDto(
              localizedMonthName(monthIndex, Locales.RU),
              monthIndex,
              defaultValue
          )
      );
    }

    final var startDate = firstDateOfYear(year);
    final var endDate = lastDateOfYear(year);

    catStats.stream()
        .filter(stat -> isDateBetween(stat.getReportDate(), startDate, endDate))
        .forEach(stat -> {
          final var monthNum = stat.getReportDate().getMonthValue();
          final var monthStat = statsByMonth.get(monthNum - 1);
          monthStat.setValue((double) stat.getDiff());
        });

    return new StatsYearDto(year, statsByMonth);
  }
}
