package ru.rshb.intech.digital.service.content.statistics;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.service.config.options.StatisticsOptions;
import ru.rshb.intech.digital.service.content.statistics.agrobit.SalaryStatsCollector;
import ru.rshb.intech.digital.service.content.statistics.agrobit.TourismStatsCollector;
import ru.rshb.intech.digital.subsystem.audit.event.AuditLogEvent;
import ru.rshb.intech.digital.subsystem.audit.event.LogLevel;
import ru.rshb.intech.digital.subsystem.events.AppEventBus;
import ru.rshb.intech.digital.subsystem.events.SubSystem;

@Slf4j
@Getter(AccessLevel.PRIVATE)
@Component
@RequiredArgsConstructor
public class StatisticsWatcher { // TODO use batch instead

  private final StatisticsOptions options;
  private final SalaryStatsCollector salaryStatsCollector;
  private final TourismStatsCollector tourismStatsCollector;
  private final AppEventBus appEventBus;

  @Scheduled(cron = "${statistics.collection.cron}")
  public void fetchStatistics() {

    if (options.isCollectionDisabled()) {
      log.info("Statistics collection disabled. Fetching canceled.");
      appEventBus.log(
          new AuditLogEvent(this, LogLevel.INFO, SubSystem.STATISTICS)
              .withTitleLocalized("audit.event.statistics.fetching.disabled.title")
              .withDescriptionLocalized("audit.event.statistics.fetching.disabled.description")
      );
      return;
    }

    log.info("Starting statistics fetching...");

    fetchAgrobitSalaryStats();
    fetchAgrobitTourismStats();

    log.info("Statistics fetching completed");
    appEventBus.log(
        new AuditLogEvent(this, LogLevel.INFO, SubSystem.STATISTICS)
            .withTitleLocalized("audit.event.statistics.fetching.completed.title")
            .withDescriptionLocalized("audit.event.statistics.fetching.completed.description")
    );
  }

  private synchronized void fetchAgrobitSalaryStats() {

    if (options.getAgrobitSalary().isCollectionDisabled()) {
      log.info("Agrobit salary statistics collection disabled. Fetching canceled");
      return;
    }

    try {
      salaryStatsCollector.collect();
    } catch (Exception e) {
      log.error("Agrobit salary statistics fetching error", e);
      appEventBus.log(
          new AuditLogEvent(this, LogLevel.ERROR, SubSystem.STATISTICS)
              .withTitleLocalized("audit.event.statistics.fetching.error.agrobit.title")
              .withDescriptionLocalized("audit.event.statistics.fetching.error.agrobit.salary.description")
              .withThrowable(e)
      );
    }
  }

  private synchronized void fetchAgrobitTourismStats() {

    if (options.getAgrobitTourism().isCollectionDisabled()) {
      log.info("Agrobit tourism statistics collection disabled. Fetching canceled.");
      return;
    }

    try {
      tourismStatsCollector.collect();
    } catch (Exception e) {
      log.error("Agrobit tourism statistics fetching error", e);
      appEventBus.log(
          new AuditLogEvent(this, LogLevel.ERROR, SubSystem.STATISTICS)
              .withTitleLocalized("audit.event.statistics.fetching.error.agrobit.title")
              .withDescriptionLocalized("audit.event.statistics.fetching.error.agrobit.tourism.description")
              .withThrowable(e)
      );
    }
  }
}
