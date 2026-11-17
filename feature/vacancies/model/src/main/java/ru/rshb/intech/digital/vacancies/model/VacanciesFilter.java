package ru.rshb.intech.digital.vacancies.model;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Builder
@Generated
@AllArgsConstructor
@NoArgsConstructor
public class VacanciesFilter {

  public static final String SORT = "sort";
  public static final String COMPANIES = "companies";
  public static final String FIELDS = "fields";
  public static final String EMPLOYMENTS = "employments";
  public static final String EXPERIENCES = "experiences";
  public static final String CITY = "city";

  private String sort;
  private String companies;
  private String fields;
  private String employment;
  private String experience;
  private String city;

  @Nonnull
  public Optional<String> getSort() {
    return Optional.ofNullable(sort);
  }

  @Nonnull
  private Set<String> toStringSet(@Nullable String value) {

    if (StringUtils.isBlank(value)) {
      return Collections.emptySet();
    }

    final var values = Arrays.asList(
        StringUtils.split(value, ",")
    );

    return new HashSet<>(values);
  }

  @Nonnull
  public Set<String> getCompanies() {
    return toStringSet(companies);
  }

  @Nonnull
  public Set<String> getFields() {
    return toStringSet(fields);
  }

  @Nonnull
  public Set<String> getEmployments() {
    return toStringSet(employment);
  }

  @Nonnull
  public Set<String> getExperiences() {
    return toStringSet(experience);
  }

  @Nonnull
  public Optional<String> getCity() {
    return StringUtils.isNotBlank(city)
        ? Optional.of(city.trim())
        : Optional.empty();
  }
}
