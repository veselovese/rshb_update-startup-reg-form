package ru.rshb.intech.digital.service.content.statistics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.statistics.forms.core.options.FormRequestStatsOptions;
import ru.rshb.intech.digital.feature.statistics.forms.core.service.FormStatsService;

@Slf4j
@Component
@RequiredArgsConstructor
public class FormStatsWatcher {

  private final FormRequestStatsOptions options;

  private final FormStatsService formStatsService;


  @Scheduled(cron = "${statistics.collection.form-requests.cron}")
  public void watchStats() {

    if (options.isCollectionDisabled()) {
      return;
    }

    try {
      formStatsService.collectAndSaveFormUsageStats();
    } catch (Exception e) {
      log.error("Form stats collection error", e);
    }
  }
}
