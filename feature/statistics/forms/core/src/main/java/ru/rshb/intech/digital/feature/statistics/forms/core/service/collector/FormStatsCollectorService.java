package ru.rshb.intech.digital.feature.statistics.forms.core.service.collector;


import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.statistics.forms.entity.FormStatsReport;

public interface FormStatsCollectorService {

  @Nonnull
  FormStatsReport collectFormUsageStatistics();
}
