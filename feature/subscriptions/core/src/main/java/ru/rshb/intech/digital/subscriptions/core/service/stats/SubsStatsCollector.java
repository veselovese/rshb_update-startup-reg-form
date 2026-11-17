package ru.rshb.intech.digital.subscriptions.core.service.stats;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.statistics.forms.api.AbstractFormUsageStatsCollector;

@Component
public class SubsStatsCollector extends AbstractFormUsageStatsCollector {

  private static final String COLLECTOR_NAME_KEY = "subscriptions.subscribe.operation.title";

  public SubsStatsCollector(@Nonnull SubsStatsProvider statsProvider) {
    super(COLLECTOR_NAME_KEY, statsProvider);
  }
}
