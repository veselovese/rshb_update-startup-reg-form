package ru.rshb.intech.digital.vacancies.hh.client;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HHFilter {

  private final Map<String, Parameter> parameters = new HashMap<>();

  @Nonnull
  public static HHFilter create() {
    return new HHFilter();
  }

  @Nonnull
  public HHFilter setParam(@Nonnull String parameter, @Nullable String value) {

    if (value == null) {
      parameters.remove(parameter);
      return this;
    }

    parameters.put(parameter, new Parameter(parameter, value));
    return this;
  }

  @Override
  public String toString() {

    final var values = parameters.values();
    if (values.isEmpty()) {
      return StringUtils.EMPTY;
    }

    return "?" + parameters.values().stream()
        .map(param -> param.getName() + "=" + param.getValue())
        .collect(Collectors.joining("&"));
  }

  @Data
  @AllArgsConstructor
  private static class Parameter {

    private String name;
    private String value;
  }
}
