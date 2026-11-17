package ru.rshb.intech.digital.vacancies.core.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.AreaDto;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.EmploymentDto;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.ExperienceDto;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.ProfessionalRoleDto;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.SpecializationDto;
import ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description.VacancyExtendedDto;

import jakarta.annotation.Nonnull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@RequiredArgsConstructor
public class LookupCache {

  private final Map<String, SpecializationDto> specializations = new HashMap<>();
  private final Map<String, EmploymentDto> employments = new HashMap<>();
  private final Map<String, ExperienceDto> experiences = new HashMap<>();
  private final Map<String, AreaDto> areas = new HashMap<>();

  @Getter
  private final ProfRoleLookup profRolesLookup;

  @Nonnull
  public Collection<SpecializationDto> specializations() {
    return specializations.values();
  }

  @Nonnull
  public Collection<EmploymentDto> employments() {
    return employments.values();
  }

  @Nonnull
  public Collection<ExperienceDto> experiences() {
    return experiences.values();
  }

  @Nonnull
  public Collection<AreaDto> areas() {
    return areas.values();
  }

  public void merge(@Nonnull Collection<VacancyExtendedDto> detailedVacancies) {

    for (final var vacancy : detailedVacancies) {

      emptyIfNull(vacancy.getProfessionalRoles())
          .stream()
          .map(profRole -> specializationFromProfRole(profRole))
          .forEach(spec -> this.specializations.put(spec.getId(), spec));

      this.employments
          .put(vacancy.getEmployment().getId(), vacancy.getEmployment());

      this.experiences
          .put(vacancy.getExperience().getId(), vacancy.getExperience());

      this.areas
          .put(vacancy.getArea().getId(), vacancy.getArea());
    }
  }

  @Nonnull
  private SpecializationDto specializationFromProfRole(@Nonnull ProfessionalRoleDto profRole) {
    final var category = profRolesLookup.findCategoryByRoleId(profRole.getId()).get();
    final var spec = new SpecializationDto();
    spec.setId(profRole.getId());
    spec.setName(profRole.getName());
    spec.setProfAreaId(category.getId());
    spec.setProfAreaName(category.getName());
    return spec;
  }
}
