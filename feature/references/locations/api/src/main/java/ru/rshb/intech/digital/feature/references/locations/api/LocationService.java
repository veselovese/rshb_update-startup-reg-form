package ru.rshb.intech.digital.feature.references.locations.api;

import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Optional;

public interface LocationService {

  @Nonnull
  List<Country> findAllCountries();

  @Nonnull
  List<Country> findCountryByTitle(@Nonnull String country);

  @Nonnull
  Optional<Country> findCountryById(@Nonnull String countryId);

  @Nonnull
  Optional<City> findCityById(@Nonnull String cityId);

  @Nonnull
  List<City> findByCountryAndCity(@Nonnull Country country, @Nonnull String city);

  @Nonnull
  List<City> findByCountryIdAndCity(@Nonnull Integer countryId, @Nonnull String cityTitle);

}
