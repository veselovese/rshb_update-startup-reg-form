package ru.rshb.intech.digital.feature.references.locations.cache.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.references.locations.cache.entity.LocationCity;
import ru.rshb.intech.digital.feature.references.locations.cache.entity.LocationCountry;

import java.util.Optional;

public interface LocationCachedService {

  @Nonnull
  Optional<LocationCountry> findCountryByExternalId(@Nonnull String id);

  boolean isCountryExist(@Nonnull String id);

  boolean isCityExist(@Nonnull String countryId, @Nonnull String cityId);

  @Nonnull
  Optional<LocationCity> findCityByExternalId(@Nonnull String countryId, @Nonnull String externalId);

}
