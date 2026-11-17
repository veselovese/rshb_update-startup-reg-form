package ru.rshb.intech.digital.feature.statistics.forms.core.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rshb.intech.digital.feature.statistics.forms.core.repository.FormStatsRepository;
import ru.rshb.intech.digital.feature.statistics.forms.core.service.collector.FormStatsCollectorService;
import ru.rshb.intech.digital.feature.statistics.forms.core.service.notification.StatsNotificationService;
import ru.rshb.intech.digital.feature.statistics.forms.entity.FormStatsReport;

@Slf4j
@Service
@RequiredArgsConstructor
public class FormStatsServiceImpl implements FormStatsService {

  private final FormStatsCollectorService formStatsCollector;

  private final FormStatsRepository formStatsRepository;

  private final StatsNotificationService notificationService;

  @Override
  @Transactional
  public void collectAndSaveFormUsageStats() {
    log.debug("Statistics collection started...");
    final var collectedStatsReport = formStatsCollector.collectFormUsageStatistics();
    final var savedStatsReport = formStatsRepository.save(collectedStatsReport);
    debugReport(savedStatsReport);
    notificationService.sendFormUsageStatistics(savedStatsReport);
    log.debug("Statistics collection queued for sending");
  }

  private void debugReport(@Nonnull FormStatsReport report) {

    final var sb = new StringBuilder();
    sb.append("\tCreated: ").append(report.getCreatedDate()).append("\n")
        .append("\tPeriod Start: ").append(report.getPeriodStartDate()).append("\n")
        .append("\tPeriod End: ").append(report.getPeriodEndDate()).append("\n")
        .append("\tStats:\n");

    for (final var stat : report.getFormStats()) {
      sb.append("\t\tForm Type:").append(stat.getFormTitle()).append("\n")
          .append("\t\tOverall from [")
          .append(stat.getOldestRecordDate()).append("]: ")
          .append(stat.getTotalUsagesCount()).append("\n")
          .append("\t\tFor week [")
          .append(stat.getReport().getPeriodStartDate())
          .append(" - ")
          .append(stat.getReport().getPeriodEndDate()).append("]: ")
          .append(stat.getDeltaUsagesCountForPeriod()).append("\n");
    }

    log.debug(sb.toString());
  }
}
