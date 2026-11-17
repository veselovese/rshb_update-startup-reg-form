package ru.rshb.intech.digital.vacancies.core.service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import ru.rshb.intech.digital.vacancies.entity.Area;

import java.util.Collection;
import java.util.List;

public interface VacancyAreaService {

  void deleteAll();

  @Nullable
  Area getByExternalIdOrNull(@Nonnull String externalId);

  @Nonnull
  List<Area> findAll();

  @Nonnull
  List<Area> saveAll(@Nonnull Collection<Area> areas);
}
