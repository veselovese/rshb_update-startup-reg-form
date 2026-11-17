package ru.rshb.intech.digital.feature.statistics.forms.core.service.collector;

import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.statistics.forms.api.FormUsageStatsCollector;
import ru.rshb.intech.digital.feature.statistics.forms.core.repository.FormStatsRepository;
import ru.rshb.intech.digital.feature.statistics.forms.entity.FormStatsReport;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FormStatsCollectorServiceImpl implements FormStatsCollectorService {

  private final FormStatsRepository formStatsRepository;
  private final List<FormUsageStatsCollector> collectors;

  @PostConstruct
  public void init() {
    logAvailableCollectors();
  }

  private void logAvailableCollectors() {
    final var sb = new StringBuilder("Found form usage stats collectors:\n");
    collectors.forEach(collector -> sb.append("\t- ").append(collector.getFormTitle()).append("\n"));
    log.debug(sb.toString());
  }

  @Nonnull
  @Override
  public FormStatsReport collectFormUsageStatistics() {

    final var newPeriodStart = LocalDateTime.now().minusWeeks(1);
    final var newPeriodEnd = LocalDateTime.now();

    final var oldReport = formStatsRepository.findFirstByOrderByCreatedDateDesc()
        .orElse(null);

    final var newStats = collectors.stream()
        .map(collector -> collector.collect(oldReport, newPeriodStart, newPeriodEnd))
        .toList();

    final var newReport = new FormStatsReport();
    newReport.setCreatedDate(LocalDateTime.now());
    newReport.setPeriodStartDate(newPeriodStart);
    newReport.setPeriodEndDate(newPeriodEnd);
    newReport.addStats(newStats);

    return newReport;
  }
}
