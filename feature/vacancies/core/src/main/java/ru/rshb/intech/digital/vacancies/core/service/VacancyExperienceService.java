package ru.rshb.intech.digital.vacancies.core.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.vacancies.entity.Experience;

import java.util.Collection;
import java.util.List;

public interface VacancyExperienceService {

  void deleteAll();

  @Nonnull
  List<Experience> findAll(@Nonnull Collection<String> externalId);

  @Nonnull
  List<Experience> findAll();

  @Nonnull
  List<Experience> saveAll(@Nonnull Collection<Experience> experiences);
}
