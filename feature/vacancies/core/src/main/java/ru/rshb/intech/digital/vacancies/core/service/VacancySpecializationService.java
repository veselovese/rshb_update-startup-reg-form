package ru.rshb.intech.digital.vacancies.core.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.vacancies.entity.Specialization;

import java.util.Collection;
import java.util.List;

public interface VacancySpecializationService {

  void deleteAll();

  @Nonnull
  List<Specialization> findAll(@Nonnull Collection<String> externalIds);

  @Nonnull
  List<Specialization> findAll();

  @Nonnull
  List<Specialization> saveAll(@Nonnull Collection<Specialization> specializations);
}
