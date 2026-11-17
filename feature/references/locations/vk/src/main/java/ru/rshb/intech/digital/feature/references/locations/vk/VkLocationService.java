package ru.rshb.intech.digital.feature.references.locations.vk;

import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.VkApiClient;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.references.locations.api.City;
import ru.rshb.intech.digital.feature.references.locations.api.Country;
import ru.rshb.intech.digital.feature.references.locations.api.LocationService;
import ru.rshb.intech.digital.feature.references.locations.vk.exception.VkLocationServiceException;
import ru.rshb.intech.digital.subsystems.integrations.vk.config.VkAuth;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Slf4j
@Service
@RequiredArgsConstructor
public class VkLocationService implements LocationService {

  public static final int MIN_QUERY_LEN = 3;
  public static final int COUNTRY_CHUNK_SIZE = 300;

  private final VkApiClient vkApiClient;
  private final VkAuth vkAuth;

  private boolean isNotAuthProvided() { // TODO
    if (vkAuth.isDisabled()) {
      log.warn("VK auth is not provided");
    }
    return vkAuth.isDisabled();
  }

  @Nonnull
  @Override
  public List<Country> findAllCountries() {

    if (isNotAuthProvided()) { // TODO
      return Collections.emptyList();
    }

    try {
      final var countryIds = IntStream.rangeClosed(0, COUNTRY_CHUNK_SIZE) // TODO
          .boxed()
          .toList();

      final var countriesResponse = vkApiClient.database()
          .getCountriesById(vkAuth.getServiceActor())
          .countryIds(countryIds)
          .lang(Lang.RU)
          .execute();

      return countriesResponse.stream()
          .filter(country -> country.getId() != null && isNotBlank(country.getTitle()))
          .map(country -> new Country(country.getId(), country.getTitle()))
          .toList();

    } catch (Exception e) {
      throw new VkLocationServiceException("Getting countries list error", e);
    }
  }

  @Nonnull
  @Override
  public List<Country> findCountryByTitle(@Nonnull String country) {

    if (isNotAuthProvided()) { // TODO
      return Collections.emptyList();
    }

    if (isBlank(country)) {
      return emptyList();
    }

    final var trimmedCountryName = country.trim();
    if (trimmedCountryName.length() < MIN_QUERY_LEN) {
      return emptyList();
    }

    return findAllCountries()
        .stream()
        .filter(c -> StringUtils.startsWithIgnoreCase(c.title(), trimmedCountryName))
        .toList();
  }

  @Nonnull
  @Override
  public Optional<Country> findCountryById(@Nonnull String countryId) {

    if (isNotAuthProvided()) { // TODO
      return Optional.empty();
    }

    if (isBlank(countryId)) {
      return Optional.empty();
    }

    try {

      final var cId = Integer.parseInt(countryId);

      final var countriesResponse = vkApiClient.database()
          .getCountriesById(vkAuth.getServiceActor())
          .countryIds(cId)
          .lang(Lang.RU)
          .execute();


      return countriesResponse.stream()
          .filter(country -> country.getId() != null && isNotBlank(country.getTitle()))
          .map(country -> new Country(country.getId(), country.getTitle()))
          .findFirst();

    } catch (Exception e) {
      throw new VkLocationServiceException("Getting country [" + countryId + "] error", e);
    }
  }

  @Nonnull
  @Override
  public Optional<City> findCityById(@Nonnull String cityId) {

    if (isNotAuthProvided()) { // TODO
      return Optional.empty();
    }

    if (isBlank(cityId)) {
      return Optional.empty();
    }

    try {

      final var cId = Integer.parseInt(cityId);

      final var citiesResponse = vkApiClient.database()
          .getCitiesById(vkAuth.getServiceActor())
          .cityIds(cId)
          .lang(Lang.RU)
          .execute();

      return citiesResponse.stream()
          .filter(city -> city.getId() != null && isNotBlank(city.getTitle()))
          .map(city -> new City(city.getId(), city.getTitle(), null, null))
          .findFirst();

    } catch (Exception e) {
      throw new VkLocationServiceException("Getting city [" + cityId + "] error", e);
    }
  }

  @Nonnull
  @Override
  public List<City> findByCountryAndCity(@Nonnull Country country, @Nonnull String city) {

    if (isNotAuthProvided()) { // TODO
      return Collections.emptyList();
    }

    log.debug("Trying to find city by country [{}] and name part [{}]", country.title(), city);

    if (isBlank(city)) {
      return emptyList();
    }

    final var trimmedCityName = city.trim();
    if (trimmedCityName.length() < MIN_QUERY_LEN) {
      return emptyList();
    }

    try {

      final var citiesResponse = vkApiClient.database()
          .getCities(vkAuth.getUserActor(), country.id())
          .q(city)
          .lang(Lang.RU)
          .execute();

      final var foundCities = citiesResponse.getItems();
      log.debug("Found cities: {}", foundCities.size());

      return foundCities.stream()
          .map(vkCity ->
                   new City(
                       vkCity.getId(),
                       vkCity.getTitle(),
                       vkCity.getArea(),
                       vkCity.getRegion()
                   )
          )
          .toList();
    } catch (Exception e) {
      log.error("Cannot get cities", e);
      return emptyList();
    }
  }

  @Nonnull
  @Override
  public List<City> findByCountryIdAndCity(@Nonnull Integer countryId, @Nonnull String cityTitle) {

    if (isNotAuthProvided()) { // TODO
      return Collections.emptyList();
    }

    return findByCountryAndCity(
        new Country(countryId, StringUtils.EMPTY),
        cityTitle
    );
  }
}
