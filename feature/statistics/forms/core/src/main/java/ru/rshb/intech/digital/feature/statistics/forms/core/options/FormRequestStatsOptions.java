package ru.rshb.intech.digital.feature.statistics.forms.core.options;

import lombok.Getter;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class FormRequestStatsOptions {

  @Value("${statistics.collection.form-requests.enabled}")
  private Boolean enabled;

  @Value("${statistics.collection.form-requests.cron}")
  private String cronSchedule;

  public boolean isCollectionEnabled() {
    return BooleanUtils.toBoolean(enabled);
  }

  public boolean isCollectionDisabled() {
    return !isCollectionEnabled();
  }
}
