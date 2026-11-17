package ru.rshb.intech.digital.feedback.core.service.report.form.statistics;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.statistics.forms.api.AbstractFormUsageStatsCollector;

@Component
public class FeedbackStatsCollector extends AbstractFormUsageStatsCollector {

  private static final String COLLECTOR_FORM_KEY = "feedback.form.title";

  public FeedbackStatsCollector(@Nonnull FeedbackStatsProvider statsProvider) {
    super(COLLECTOR_FORM_KEY, statsProvider);
  }
}
