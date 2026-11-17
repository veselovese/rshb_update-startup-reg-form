package ru.rshb.intech.digital.service.content.statistics.agrobit;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.tourism.TourismAvgStatsEntry;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.tourism.TourismStatsEntry;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism.SfctTourismStatsRepository;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism.TourismStatsType;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism.queries.TourismAvgStatsParams;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism.queries.TourismStatsByTypeParams;
import ru.rshb.intech.digital.statistics.agrobit.tourism.dto.TourismRecreationTypeImpl;
import ru.rshb.intech.digital.statistics.agrobit.tourism.entity.TourismAvgStatsEntity;
import ru.rshb.intech.digital.statistics.agrobit.tourism.entity.TourismCategoryStatsEntity;
import ru.rshb.intech.digital.statistics.agrobit.tourism.entity.TourismType;
import ru.rshb.intech.digital.statistics.agrobit.tourism.repository.TourismAvgStatsRepository;
import ru.rshb.intech.digital.statistics.agrobit.tourism.repository.TourismCategoryStatsRepository;
import ru.rshb.intech.digital.statistics.agrobit.tourism.repository.TourismTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeUtilz.firstDateOfYear;
import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeUtilz.lastDateOfYear;

@Slf4j
@Component
@RequiredArgsConstructor
public class TourismStatsCollector {

  private final TourismTypeRepository tourismTypeRepository;
  private final TourismCategoryStatsRepository tourismCategoryStatsRepository;
  private final TourismAvgStatsRepository tourismAvgStatsRepository;
  private final SfctTourismStatsRepository sfctTourismStatsRepository;

  @Transactional
  public void collect() {

    log.info("Collecting tourism stats...");
    final var tourismTypes = tourismTypeRepository.findAllByEnabledIsTrueOrderByTitleAsc();

    log.info("Wiping tourism stats...");
    tourismCategoryStatsRepository.deleteAll();
    tourismAvgStatsRepository.deleteAll();

    collectCheckStatsAvg(tourismTypes);
    collectCheckStatsByRecreationTypes(tourismTypes);

    collectPriceStatsAvg(tourismTypes);
    collectPriceStatsByRecreationTypes(tourismTypes);

    collectToursNumberAvg(tourismTypes);
    collectToursNumberStatsByRecreationTypes(tourismTypes);

    collectAllAvg();
  }

  private void collectCheckStatsAvg(@Nonnull List<TourismType> tourismTypes) {
    log.info("Collecting tourism average check stats...");
    collectTourismStatsAvg(TourismStatsType.AVG_CHECK, tourismTypes);
  }

  private void collectCheckStatsByRecreationTypes(@Nonnull List<TourismType> tourismTypes) {
    log.info("Collecting tourism avg check stats by recreation types...");
    collectTourismStats(TourismStatsType.AVG_CHECK, tourismTypes);
  }

  private void collectPriceStatsAvg(@Nonnull List<TourismType> tourismTypes) {
    log.info("Collecting tourism average price stats...");
    collectTourismStatsAvg(TourismStatsType.AVG_TOUR_PRICE, tourismTypes);
  }

  private void collectPriceStatsByRecreationTypes(@Nonnull List<TourismType> tourismTypes) {
    log.info("Collecting tourism tour price stats by recreation types ...");
    collectTourismStats(TourismStatsType.AVG_TOUR_PRICE, tourismTypes);
  }

  private void collectToursNumberAvg(@Nonnull List<TourismType> tourismTypes) {
    log.info("Collecting tourism tours number stats...");
    collectTourismStatsAvg(TourismStatsType.TOUR_NUMBER, tourismTypes);
  }

  private void collectToursNumberStatsByRecreationTypes(@Nonnull List<TourismType> tourismTypes) {
    log.info("Collecting tourism tours number stats...");
    collectTourismStats(TourismStatsType.TOUR_NUMBER, tourismTypes);
  }

  private void collectTourismStatsAvg(@Nonnull TourismStatsType statsType, @Nonnull List<TourismType> tourismTypes) {

    for (final var tourismType : tourismTypes) {
      log.info("Saving tourism avg stats for category [{}]...", tourismType.getTitle());

      final var recreationTypes = tourismType.getRecreationTypes()
          .stream()
          .map(recreationType -> recreationType.getTitle())
          .collect(Collectors.toSet());

      final var stats = sfctTourismStatsRepository.findAvg(
          TourismAvgStatsParams.builder()
              .statsType(statsType)
              .tourismType(tourismType.getTitle())
              .recreations(recreationTypes)
              .build()
      );

      tourismAvgStatsRepository.saveAll(
          stats.stream()
              .map(statsEntry -> mapAvg(statsEntry, statsType))
              .toList()
      );
    }
  }

  private void collectTourismStats(@Nonnull TourismStatsType statsType, @Nonnull List<TourismType> tourismTypes) {

    for (final var tourismType : tourismTypes) {

      log.info("Saving tourism stats for category [{}]...", tourismType.getTitle());

      final var recreationTypes = tourismType.getRecreationTypes()
          .stream()
          .map(recreationType -> new TourismRecreationTypeImpl(recreationType))
          .toList();

      for (final var recreationType : recreationTypes) {
        final var stats = sfctTourismStatsRepository.find(
            TourismStatsByTypeParams.builder()
                .statsType(statsType)
                .tourismType(tourismType.getTitle())
                .recreationType(recreationType.getTitle())
                .categories(recreationType.getCategories())
                .periodStart(firstDateOfYear(2020))
                .periodEnd(lastDateOfYear())
                .build()
        );

        tourismCategoryStatsRepository.saveAll(
            stats.stream()
                .map(statEntry -> map(statEntry, statsType))
                .toList()
        );
      }
    }
  }

  private void collectAllAvg() {

    log.info("Collecting all average stats...");

    for (final var statType : TourismStatsType.values()) {

      log.info("Saving all average stats for [{}] KPI parameter...", statType.getKpiParamName());
      final var stats = sfctTourismStatsRepository.findAllByStatsType(statType);

      tourismAvgStatsRepository.saveAll(
          stats.stream()
              .map(stat -> mapAvg(stat, statType))
              .toList()
      );
    }
  }

  @Nonnull
  private TourismAvgStatsEntity mapAvg(@Nonnull TourismAvgStatsEntry statsEntry, @Nonnull TourismStatsType statsType) {
    final var localEntry = new TourismAvgStatsEntity();
    localEntry.setReportDate(statsEntry.getReportDate());
    localEntry.setTourismType(statsEntry.getTourismType());
    localEntry.setStatsType(statsType.getKpiParamName());
    localEntry.setValue(statsEntry.getIndicatorValue());
    localEntry.setDiff(statsEntry.getIndicatorDiff());
    localEntry.setDiffPct(statsEntry.getIndicatorDiffPct());
    return localEntry;
  }

  @Nonnull
  private TourismCategoryStatsEntity map(@Nonnull TourismStatsEntry statsEntry, @Nonnull TourismStatsType statsType) {
    final var localEntry = new TourismCategoryStatsEntity();
    localEntry.setReportDate(statsEntry.getReportDate());
    localEntry.setTourismType(statsEntry.getTourismType());
    localEntry.setRecreationType(statsEntry.getRecreationType());
    localEntry.setStatsType(statsType.getKpiParamName());
    localEntry.setValue(statsEntry.getIndicatorValue());
    localEntry.setDiff(statsEntry.getIndicatorDiff());
    localEntry.setDiffPct(statsEntry.getIndicatorDiffPct());
    return localEntry;
  }

}
