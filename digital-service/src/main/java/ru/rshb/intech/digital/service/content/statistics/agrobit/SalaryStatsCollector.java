package ru.rshb.intech.digital.service.content.statistics.agrobit;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.statistics.agrobit.salary.dto.PositionCategoryImpl;
import ru.rshb.intech.digital.statistics.agrobit.salary.entity.SalaryAvgStatsEntity;
import ru.rshb.intech.digital.statistics.agrobit.salary.entity.SalaryCategoryStatsEntity;
import ru.rshb.intech.digital.statistics.agrobit.salary.repository.SalaryAvgStatsRepository;
import ru.rshb.intech.digital.statistics.agrobit.salary.repository.SalaryCategoryStatsRepository;
import ru.rshb.intech.digital.statistics.agrobit.salary.repository.SalaryPositionCategoryRepository;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.SfctSalaryCategoryStatsEntry;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.SfctSalaryPositionStatsEntry;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.salary.SfctSalaryAvgStatsRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalaryStatsCollector {

  private final SalaryPositionCategoryRepository salaryPositionCategoryRepository;
  private final SfctSalaryAvgStatsRepository salaryStatsRemoteRepository;
  private final SalaryAvgStatsRepository salaryStatsLocalRepository;
  private final SalaryCategoryStatsRepository salaryCategoryStatsRepository;

  @Transactional
  public void collect() {
    collectSalaryCategoryStats();
    collectSalaryPositionStats();
  }

  private void collectSalaryCategoryStats() {

    log.info("Collecting salary stats by categories...");
    final var categories = salaryPositionCategoryRepository.findAllByEnabledTrueOrderByNameAsc()
        .stream()
        .map(cat -> new PositionCategoryImpl(cat))
        .toList();

    log.info("Wiping salary category stats...");
    salaryCategoryStatsRepository.deleteAll();

    for (final var category : categories) {
      log.info("Saving salary category stats for category [{}]...", category.getCategoryName());
      final var categoryStats = salaryStatsRemoteRepository.findAvgSalaryByCategoryMonthOverMonth(category)
          .stream()
          .map(sfctEntry -> map(sfctEntry))
          .toList();
      salaryCategoryStatsRepository.saveAll(categoryStats);
    }

    log.info("Saving salary category stats for all categories...");
    final var categoryStats = salaryStatsRemoteRepository.findAvgSalaryByAllPositionsMonthOverMonth()
        .stream()
        .map(sfctEntry -> map(sfctEntry))
        .toList();
    salaryCategoryStatsRepository.saveAll(categoryStats);
  }

  @Nonnull
  private SalaryCategoryStatsEntity map(@Nonnull SfctSalaryCategoryStatsEntry sfctEntry) {
    final var entity = new SalaryCategoryStatsEntity();
    entity.setReportDate(sfctEntry.getReportDate());
    entity.setCategory(sfctEntry.getCategory());
    entity.setValue(sfctEntry.getIndicatorValue());
    entity.setDiff(sfctEntry.getIndicatorDiff());
    return entity;
  }

  private void collectSalaryPositionStats() {

    log.info("Collecting salary stats by positions...");

    final var categories = salaryPositionCategoryRepository.findAllByEnabledTrueOrderByNameAsc();
    final var startYear = salaryStatsRemoteRepository.findDataMinYear();

    salaryStatsLocalRepository.deleteAll();

    for (final var category : categories) {

      log.info("Saving salary stats for [{}] category...", category.getName());

      final var positions = category.getPositions()
          .stream()
          .map(position -> position.getName())
          .toList();

      for (final var position : positions) {
        log.info("Saving salary stats for [{}] position...", position);
        final var sfctSalaryByPositionStats = salaryStatsRemoteRepository.findAvgSalaryByPosition(startYear, category.getName(), position);
        final var localSalaryStats = sfctSalaryByPositionStats.stream()
            .map(sfctStat -> map(sfctStat))
            .toList();

        salaryStatsLocalRepository.saveAll(localSalaryStats);
      }
    }

    log.info("Saving salary stats for all positions...");
    final var sfctSalaryStats = salaryStatsRemoteRepository.findAvgSalaryByAllPositions(startYear);
    final var localSalaryStats = sfctSalaryStats.stream()
        .map(sfctStat -> map(sfctStat))
        .toList();

    salaryStatsLocalRepository.saveAll(localSalaryStats);
  }

  @Nonnull
  private SalaryAvgStatsEntity map(@Nonnull SfctSalaryPositionStatsEntry sfctEntry) {
    final var localStats = new SalaryAvgStatsEntity();
    localStats.setReportDate(sfctEntry.getReportDate());
    localStats.setCategory(sfctEntry.getCategory());
    localStats.setPosition(sfctEntry.getPosition());
    localStats.setValue(sfctEntry.getIndicatorValue());
    localStats.setDiff(sfctEntry.getIndicatorDiff());
    localStats.setDiffPct(sfctEntry.getIndicatorDiffPct());
    return localStats;
  }
}
