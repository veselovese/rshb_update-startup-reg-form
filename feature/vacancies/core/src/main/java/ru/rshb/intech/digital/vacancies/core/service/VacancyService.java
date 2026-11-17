package ru.rshb.intech.digital.vacancies.core.service;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.PageRequest;
import ru.rshb.intech.digital.vacancies.entity.Vacancy;
import ru.rshb.intech.digital.vacancies.hh.dto.filter.VacancyFilterLookupDto;
import ru.rshb.intech.digital.vacancies.model.VacanciesFilter;
import ru.rshb.intech.digital.vacancies.model.VacancyDto;
import ru.rshb.intech.digital.vacancies.model.VacancyHighlightDto;
import ru.rshb.intech.digtial.commons.dto.Page;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface VacancyService {

  @Nonnull
  Page<VacancyHighlightDto> findAll(@Nonnull VacanciesFilter filter, @Nonnull PageRequest pageRequest);

  @Nonnull
  Optional<VacancyDto> findByExternalId(@Nonnull String externalId);

  @Nonnull
  VacancyDto getByExternalId(@Nonnull String externalId);

  @Nonnull
  VacancyFilterLookupDto collectFilters();

  void cleanup();

  @Nonnull
  List<Vacancy> saveAll(@Nonnull Collection<Vacancy> vacancies);
}
