package ru.rshb.intech.digital.subscriptions.core.service.stats;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.statistics.forms.api.AbstractFormUsageStatsCollector;

@Component
public class UnsubsStatsCollector extends AbstractFormUsageStatsCollector {

  private static final String COLLECTOR_TITLE_KEY = "subscriptions.unsubscribe.operation.title";

  public UnsubsStatsCollector(@Nonnull UnsubsStatsProvider statsProvider) {
    super(COLLECTOR_TITLE_KEY, statsProvider);
  }
}
