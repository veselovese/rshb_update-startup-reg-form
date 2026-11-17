package ru.rshb.intech.digital.feature.contests.chef.service.contestant.form.statistics;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.statistics.forms.api.AbstractFormUsageStatsCollector;

@Component
public class ChefStatsCollector extends AbstractFormUsageStatsCollector {

  private static final String COLLECTOR_NAME_KEY = "contest.chef.form.title";

  public ChefStatsCollector(@Nonnull ChefStatsService statsProvider) {
    super(COLLECTOR_NAME_KEY, statsProvider);
  }
}
