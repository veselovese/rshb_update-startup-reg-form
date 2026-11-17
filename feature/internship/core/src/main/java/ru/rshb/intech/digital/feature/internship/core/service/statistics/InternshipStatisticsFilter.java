package ru.rshb.intech.digital.feature.internship.core.service.statistics;

import jakarta.annotation.Nonnull;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class InternshipStatisticsFilter {

  private final List<String> fields;

  public InternshipStatisticsFilter(@Nonnull String fieldsValues) {
    this.fields = StringUtils.isNotBlank(fieldsValues)
        ? Arrays.asList(fieldsValues.split(","))
        : List.of();
  }

  @Nonnull
  public List<String> fields() {
    return fields;
  }
}
