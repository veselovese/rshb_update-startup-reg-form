package ru.rshb.intech.digital.vacancies.core.service;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import ru.rshb.intech.digital.vacancies.entity.Area;
import ru.rshb.intech.digital.vacancies.entity.Company;
import ru.rshb.intech.digital.vacancies.entity.Employment;
import ru.rshb.intech.digital.vacancies.entity.Experience;
import ru.rshb.intech.digital.vacancies.entity.Specialization;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ReferenceEntityCache {

  private final Map<String, Company> companies = new HashMap<>();
  private final Map<String, Specialization> specializations = new HashMap<>();
  private final Map<String, Employment> employments = new HashMap<>();
  private final Map<String, Experience> experiences = new HashMap<>();
  private final Map<String, Area> areas = new HashMap<>();

  public ReferenceEntityCache companies(@Nonnull List<Company> companies) {
    this.companies.clear();
    this.companies.putAll(
        companies.stream()
            .collect(Collectors.toMap(Company::getExternalId, company -> company))
    );
    return this;
  }

  @Nonnull
  public ReferenceEntityCache specializations(@Nonnull List<Specialization> specializations) {
    this.specializations.clear();
    this.specializations.putAll(
        specializations.stream()
            .collect(Collectors.toMap(Specialization::getExternalId, specialization -> specialization))
    );
    return this;
  }

  @Nonnull
  public Set<Specialization> findSpecializationsById(@Nonnull Collection<String> ids) {
    return ids.stream()
        .map(id -> specializations.get(id))
        .filter(Objects::nonNull)
        .collect(Collectors.toSet());
  }

  @Nonnull
  public ReferenceEntityCache employments(@Nonnull List<Employment> employments) {
    this.employments.clear();
    this.employments.putAll(
        employments.stream()
            .collect(Collectors.toMap(Employment::getExternalId, employment -> employment))
    );
    return this;
  }

  @Nonnull
  public ReferenceEntityCache experiences(@Nonnull List<Experience> experiences) {
    this.experiences.clear();
    this.experiences.putAll(
        experiences.stream()
            .collect(Collectors.toMap(Experience::getExternalId, experience -> experience))
    );
    return this;
  }

  @Nonnull
  public ReferenceEntityCache areas(@Nonnull List<Area> areas) {
    this.areas.clear();
    this.areas.putAll(
        areas.stream()
            .collect(Collectors.toMap(Area::getExternalId, area -> area))
    );
    return this;
  }
}
