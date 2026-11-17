package ru.rshb.intech.digital.feature.internship.core.service.registration.statistics;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.statistics.forms.api.AbstractFormUsageStatsCollector;

@Component("internshipRegistrationStatsCollector")
public class RegistrationStatsCollector extends AbstractFormUsageStatsCollector {

  private static final String FORM_TITLE_KEY = "internship.registration.form.title";

  public RegistrationStatsCollector(@Nonnull RegistrationStatsProvider statsProvider) {
    super(FORM_TITLE_KEY, statsProvider);
  }
}
