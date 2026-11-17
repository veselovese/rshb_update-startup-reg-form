package ru.rshb.intech.digital.statistics.agrobit.tourism.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.statistics.agrobit.commons.PlotBundleDto;
import ru.rshb.intech.digital.statistics.agrobit.commons.StatsPlotter;
import ru.rshb.intech.digital.statistics.agrobit.commons.ValueDiffStat;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.tourism.TourismTypeCategory;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism.TourismStatsType;
import ru.rshb.intech.digital.statistics.agrobit.tourism.entity.TourismAvgStatsEntity;
import ru.rshb.intech.digital.statistics.agrobit.tourism.entity.TourismStatsFilter;
import ru.rshb.intech.digital.statistics.agrobit.tourism.repository.RecreationTypeRepository;
import ru.rshb.intech.digital.statistics.agrobit.tourism.repository.TourismAvgStatsRepository;
import ru.rshb.intech.digital.statistics.agrobit.tourism.repository.TourismStatsRepository;
import ru.rshb.intech.digital.statistics.agrobit.tourism.repository.TourismTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;


@Service
@RequiredArgsConstructor
public class TourismStatsServiceImpl implements TourismStatsService {

  private static final String SUB_CATEGORY_ALL = "Все";

  private final TourismTypeRepository tourismTypeRepository;
  private final RecreationTypeRepository recreationTypeRepository;
  private final TourismStatsRepository tourismStatsRepository;
  private final TourismAvgStatsRepository tourismAvgStatsRepository;
  private final StatsPlotter statsPlotter = new StatsPlotter();


  @Nonnull
  @Override
  public PlotBundleDto getCheckStatsAvg() {
    return getAvgStats(TourismStatsType.AVG_CHECK);
  }

  @Nonnull
  @Override
  public PlotBundleDto getCheckStats(@Nonnull TourismStatsFilter filter) {
    return getCategoryStats(TourismStatsType.AVG_CHECK, filter);
  }

  @Nonnull
  private PlotBundleDto getCategoryStats(@Nonnull TourismStatsType statsType, @Nonnull TourismStatsFilter filter) {

    final var tourismTypeId = filter.getCategoryId();
    final var recreationTypeIds = filter.getRecreationTypeIds();

    if (isBlank(tourismTypeId) && recreationTypeIds.isEmpty()) {
      return getAllStats(statsType);
    }

    if (!recreationTypeIds.isEmpty()) {
      return getStatsByRecreationTypeIds(statsType, recreationTypeIds);
    }

    return getStatsByTourismType(statsType, tourismTypeId);
  }

  @Nonnull
  private PlotBundleDto getAvgStats(@Nonnull TourismStatsType statsType) {

    final var tourismTypes = tourismTypeRepository.findAllByEnabledIsTrueOrderByTitleAsc();

    final var statsBuf = new ArrayList<ValueDiffStat>();

    for (final var tourismType : tourismTypes) {
      final var stats = tourismAvgStatsRepository.findAllByStatsTypeAndTourismTypeOrderByStatsType(statsType.getKpiParamName(), tourismType.getTitle())
          .stream()
          .map(s -> mapStatToValue(s))
          .toList();

      statsBuf.addAll(stats);
    }

    statsBuf.addAll(
        tourismAvgStatsRepository.findAllByStatsTypeAndTourismTypeOrderByStatsType(statsType.getKpiParamName(), TourismTypeCategory.ALL.getTypeName())
            .stream()
            .map(s -> mapStatToValue(s))
            .toList()
    );

    return statsPlotter.groupStatsData(statsBuf);
  }

  @Nonnull
  private ValueDiffStat mapStatToValue(@Nonnull TourismAvgStatsEntity stat) {
    final var value = new ValueDiffStat();
    value.setDate(stat.getReportDate());
    value.setCategory(stat.getStatsType());
    value.setSubType(stat.getTourismType());
    value.setValue(stat.getValue());
    value.setValueDiff(stat.getDiff());
    value.setValueDiffPct(stat.getDiffPct());
    return value;
  }

  @Nonnull
  @Override
  public PlotBundleDto getPriceStats(@Nonnull TourismStatsFilter filter) {
    return getCategoryStats(TourismStatsType.AVG_TOUR_PRICE, filter);
  }

  @Nonnull
  @Override
  public PlotBundleDto getPriceStatsAvg() {
    return getAvgStats(TourismStatsType.AVG_TOUR_PRICE);
  }

  @Nonnull
  @Override
  public PlotBundleDto getTourNumberStats(@Nonnull TourismStatsFilter filter) {
    return getCategoryStats(TourismStatsType.TOUR_NUMBER, filter);
  }

  @Nonnull
  @Override
  public PlotBundleDto getTourNumberStatsAvg() {
    return getAvgStats(TourismStatsType.TOUR_NUMBER);
  }

  @Nonnull
  private PlotBundleDto getAllStats(@Nonnull TourismStatsType statsType) {

    final var statsBuf = new ArrayList<ValueDiffStat>();

    final var recreationTitles = recreationTypeRepository.findAll()
        .stream()
        .map(r -> r.getTitle())
        .collect(Collectors.toSet());

    for (final var recreationTitle : recreationTitles) {
      final var stats = tourismStatsRepository.findAllByStatsTypeAndRecreationTypes(statsType.getKpiParamName(), Set.of(recreationTitle));
      statsBuf.addAll(stats);
    }

    statsBuf.addAll(
        tourismStatsRepository.findAllByStatsTypeAndRecreationTypes(statsType.getKpiParamName(), recreationTitles, SUB_CATEGORY_ALL)
    );

    return statsPlotter.groupStatsData(statsBuf);
  }

  @Nonnull
  private PlotBundleDto getStatsByRecreationTypeIds(@Nonnull TourismStatsType statsType,
                                                    @Nonnull List<String> recreationTypeIds) {

    final var nonEmptyRecreationIds = recreationTypeIds.stream()
        .filter(id -> isNotBlank(id))
        .toList();

    final var recreationTypes = recreationTypeRepository.findAllByExternalIdInAndEnabledIsTrueOrderByTitleAsc(nonEmptyRecreationIds);

    if (recreationTypes.isEmpty()) {
      return new PlotBundleDto(List.of());
    }

    final var statTypeName = statsType.getKpiParamName();

    final var statsBuf = new ArrayList<ValueDiffStat>();

    for (final var recreationType : recreationTypes) {
      final var stats = tourismStatsRepository.findAllByStatsTypeAndRecreationTypes(statTypeName, Set.of(recreationType.getTitle()));
      statsBuf.addAll(stats);
    }

    return statsPlotter.groupStatsData(statsBuf);
  }

  @Nonnull
  private PlotBundleDto getStatsByTourismType(@Nonnull TourismStatsType statsType, @Nonnull String tourismTypeId) {

    final var tourismType = tourismTypeRepository.findByExternalIdIgnoreCase(tourismTypeId)
        .orElse(null);

    if (tourismType == null) {
      return new PlotBundleDto(List.of());
    }

    final var recreations = tourismType.getRecreationTypes();
    final var recreationTitles = recreations.stream()
        .map(r -> r.getTitle())
        .collect(Collectors.toSet());

    final var statsBuf = new ArrayList<ValueDiffStat>();
    final var stats = tourismStatsRepository.findAllByStatsTypeAndRecreationTypes(statsType.getKpiParamName(), recreationTitles);
    statsBuf.addAll(stats);
    statsBuf.addAll(
        tourismStatsRepository.findAllByStatsTypeAndRecreationTypes(statsType.getKpiParamName(), recreationTitles, SUB_CATEGORY_ALL)
    );

    return statsPlotter.groupStatsData(statsBuf);
  }
}
