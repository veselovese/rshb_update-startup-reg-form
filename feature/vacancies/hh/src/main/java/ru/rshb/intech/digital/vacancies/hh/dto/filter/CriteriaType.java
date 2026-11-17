package ru.rshb.intech.digital.vacancies.hh.dto.filter;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.annotation.Nonnull;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

@JsonDeserialize(using = CriteriaTypeDeserializer.class)
@Generated
@RequiredArgsConstructor
public enum CriteriaType {

  SINGLE("single"),

  MULTIPLE("multiple");

  private final String key;

  @Nonnull
  public static CriteriaType parse(String value) {
    return Arrays.stream(values())
        .filter(criteriaType -> StringUtils.equalsIgnoreCase(criteriaType.key, value))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unknown criteria: " + value));
  }

  @JsonValue
  public String getKey() {
    return key;
  }
}
