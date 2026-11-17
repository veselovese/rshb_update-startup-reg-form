package ru.rshb.intech.digital.subscriptions.core.service.stats;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.statistics.forms.api.AbstractFormUsageStatsCollector;

@Component
public class ActiveSubsStatsCollector extends AbstractFormUsageStatsCollector {

  private static final String COLLECTOR_TITLE_KEY = "subscriptions.subscribe.active.operation.title";

  public ActiveSubsStatsCollector(@Nonnull ActiveSubsStatsProvider statsProvider) {
    super(COLLECTOR_TITLE_KEY, statsProvider);
  }
}
