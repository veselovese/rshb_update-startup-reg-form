package ru.rshb.intech.digital.feature.statistics.forms.api;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import ru.rshb.intech.digital.feature.statistics.forms.entity.FormStatsReport;
import ru.rshb.intech.digital.feature.statistics.forms.entity.FormUsageStats;

import java.time.LocalDateTime;

public interface FormUsageStatsCollector {

  @Nonnull
  String getFormTitle();

  @Nonnull
  FormUsageStats collect(@Nullable FormStatsReport oldReport,
                         @Nonnull LocalDateTime newPeriodStart,
                         @Nonnull LocalDateTime newPeriodEnd);
}
