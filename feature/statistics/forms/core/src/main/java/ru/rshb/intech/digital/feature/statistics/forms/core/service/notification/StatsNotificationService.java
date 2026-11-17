package ru.rshb.intech.digital.feature.statistics.forms.core.service.notification;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.statistics.forms.entity.FormStatsReport;

public interface StatsNotificationService {

  void sendFormUsageStatistics(@Nonnull FormStatsReport formUsageReport);
}
