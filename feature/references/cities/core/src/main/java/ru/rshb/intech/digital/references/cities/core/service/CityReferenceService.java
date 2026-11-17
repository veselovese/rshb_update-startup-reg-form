package ru.rshb.intech.digital.references.cities.core.service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import ru.rshb.intech.digital.references.cities.dto.CityDto;

import java.util.List;
import java.util.Optional;

public interface CityReferenceService {

  @Nonnull
  List<CityDto> findByName(@Nonnull String nameTemplate);

  @Nonnull
  String getCityWithRegionByIdOrEmpty(@Nullable String cityId);

  @Nonnull
  Optional<String> findCityWithRegionById(@Nonnull String cityId);

  boolean isCityExistById(@Nonnull String cityId);
}
