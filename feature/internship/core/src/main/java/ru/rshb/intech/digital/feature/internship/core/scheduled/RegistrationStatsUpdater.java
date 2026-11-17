package ru.rshb.intech.digital.feature.internship.core.scheduled;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.internship.core.service.statistics.InternshipStatisticsService;

/**
 * Сборщик статистических данных о динамике количества заявок на стажировку
 * (используется, в частности, в разделе "Инфографика).
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationStatsUpdater {

  private final InternshipStatisticsService statisticsService;

  @Value("${statistics.collection.internship.registration.enabled}")
  private Boolean collectionEnabled;

  @Scheduled(cron = "${statistics.collection.internship.registration.cron}")
  @Transactional
  public void collectMonthlyStats() {

    if (!collectionEnabled) {
      log.info("Registration stats updated: DISABLED");
      return;
    }

    try {
      log.info("Registration stats updater: START");
      statisticsService.refreshMonthlyStats();
      log.info("Registration stats updater: FINISH");
    } catch (Exception e) {
      log.error("Registration stats collection error", e);
    }
  }

}
