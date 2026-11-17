package ru.rshb.intech.digital.vacancies.core.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.vacancies.entity.Employment;

import java.util.Collection;
import java.util.List;

public interface VacancyEmploymentService {

  void deleteAll();

  @Nonnull
  List<Employment> findAll(@Nonnull Collection<String> externalIds);

  @Nonnull
  List<Employment> findAll();

  @Nonnull
  List<Employment> saveAll(@Nonnull Collection<Employment> employments);
}
