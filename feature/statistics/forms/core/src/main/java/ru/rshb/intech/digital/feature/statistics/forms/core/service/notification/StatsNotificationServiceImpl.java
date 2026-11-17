package ru.rshb.intech.digital.feature.statistics.forms.core.service.notification;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.feature.statistics.forms.entity.FormStatsReport;
import ru.rshb.intech.digital.notifications.api.Notification;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;
import ru.rshb.intech.digital.notifications.internal.InternalNotificationTopic;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE;

@Service
@RequiredArgsConstructor
public class StatsNotificationServiceImpl implements StatsNotificationService {

  private final InternalNotificationService internalNotificationService;


  @Override
  public void sendFormUsageStatistics(@Nonnull FormStatsReport report) {
    internalNotificationService.send(
        new Notification(message(report))
    );
  }

  @Nonnull
  private NotificationMessage message(@Nonnull FormStatsReport report) {

    final var templateValues = new Values()
        .put("report", report)
        .put("periodStart", SIMPLE_DATE.format(report.getPeriodStartDate()))
        .put("periodEnd", SIMPLE_DATE.format(report.getPeriodEndDate()));

    return NotificationMessage.builder()
        .topic(InternalNotificationTopic.FORM_USAGE_STATISTICS.getKey())
        .useDefaultSubject(true)
        .values(templateValues)
        .build();
  }
}
