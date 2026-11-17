package ru.rshb.intech.digital.feature.references.locations.cache.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.references.locations.api.City;
import ru.rshb.intech.digital.feature.references.locations.api.Country;
import ru.rshb.intech.digital.feature.references.locations.api.LocationService;
import ru.rshb.intech.digital.feature.references.locations.cache.entity.LocationCity;
import ru.rshb.intech.digital.feature.references.locations.cache.entity.LocationCountry;
import ru.rshb.intech.digital.feature.references.locations.cache.repository.LocationCityRepository;
import ru.rshb.intech.digital.feature.references.locations.cache.repository.LocationCountryRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationCachedServiceImpl implements LocationCachedService {

  private final LocationCountryRepository countryRepository;
  private final LocationCityRepository cityRepository;
  private final LocationService locationService;


  @Nonnull
  @Override
  public Optional<LocationCountry> findCountryByExternalId(@Nonnull String externalId) {

    final var cachedCountry = countryRepository.findByExternalIdIgnoreCase(externalId);
    if (cachedCountry.isPresent()) {
      return cachedCountry;
    }

    final var providedCountry = locationService.findCountryById(externalId);
    if (providedCountry.isEmpty()) {
      return Optional.empty();
    }

    final var savedValue = saveInCache(providedCountry.get());

    return Optional.of(savedValue);
  }

  @Nonnull
  public LocationCountry saveInCache(@Nonnull Country country) {
    return countryRepository.save(
        new LocationCountry(
            String.valueOf(country.id()),
            country.title()
        )
    );
  }

  @Override
  public boolean isCountryExist(@Nonnull String id) {

    final var cachedCountryExist = countryRepository.existsByExternalIdIgnoreCase(id);
    if (cachedCountryExist) {
      return true;
    }

    final var providedCountry = locationService.findCountryById(id);
    if (providedCountry.isEmpty()) {
      return false;
    }

    saveInCache(providedCountry.get());

    return true;
  }

  @Override
  public boolean isCityExist(@Nonnull String countryId, @Nonnull String cityId) {

    final var cachedCountry = findCountryByExternalId(countryId);
    if (cachedCountry.isEmpty()) {
      return false;
    }

    final var cachedCityExist = cityRepository.existsByExternalIdIgnoreCase(cityId);
    if (cachedCityExist) {
      return true;
    }

    final var providedCity = locationService.findCityById(cityId);
    if (providedCity.isEmpty()) {
      return false;
    }

    saveInCache(cachedCountry.get(), providedCity.get());

    return cityRepository.existsByExternalIdIgnoreCase(cityId);
  }

  @Nonnull
  public LocationCity saveInCache(@Nonnull LocationCountry country, @Nonnull City city) {
    return cityRepository.save(
        new LocationCity(
            country,
            String.valueOf(city.id()),
            city.title()
        )
    );
  }

  @Nonnull
  @Override
  public Optional<LocationCity> findCityByExternalId(@Nonnull String countryId, @Nonnull String externalId) {

    final var cachedCountry = findCountryByExternalId(countryId);
    if (cachedCountry.isEmpty()) {
      return Optional.empty();
    }

    final var cachedCity = cityRepository.findByExternalIdIgnoreCase(externalId);
    if (cachedCity.isPresent()) {
      return cachedCity;
    }

    final var providedCity = locationService.findCityById(externalId);
    if (providedCity.isEmpty()) {
      return Optional.empty();
    }

    final var savedValue = saveInCache(cachedCountry.get(), providedCity.get());

    return Optional.of(savedValue);
  }
}
