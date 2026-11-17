package ru.rshb.intech.digital.feature.innovations.core.service.general.form.statistics;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.statistics.forms.api.AbstractFormUsageStatsCollector;

@Component("innovationsGeneralRequestStatsCollector")
public class GeneralRequestStatsCollector extends AbstractFormUsageStatsCollector {

  private static final String COLLECTOR_FORM_KEY = "innovations.request.form.title";

  public GeneralRequestStatsCollector(@Nonnull GeneralRequestStatsProvider statsProvider) {
    super(COLLECTOR_FORM_KEY, statsProvider);
  }
}
