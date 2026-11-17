package ru.rshb.intech.digital.references.cities.core.service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.references.cities.core.mapper.CityMapper;
import ru.rshb.intech.digital.references.cities.core.repository.CityRepository;
import ru.rshb.intech.digital.references.cities.dto.CityDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
@RequiredArgsConstructor
public class CityReferenceServiceImpl implements CityReferenceService {

  private static final int RESULTS_OUTPUT_LIMIT = 5;
  private static final int MIN_QUERY_LENGTH = 2;

  private final CityMapper cityMapper;
  private final CityRepository cityRepository;
  private final CityNameNormalizer cityNameNormalizer;


  @Nonnull
  @Override
  public List<CityDto> findByName(@Nonnull String cityNameQuery) {

    if (StringUtils.trim(cityNameQuery).length() < MIN_QUERY_LENGTH) {
      return Collections.emptyList();
    }

    final var partialName = cityNameNormalizer.normalize(cityNameQuery);
    final var pageable = PageRequest.of(0, RESULTS_OUTPUT_LIMIT);

    final var foundCities = cityRepository.findByTitleContainingIgnoreCaseOrderByTitle(partialName, pageable)
        .stream()
        .sorted(new CityNameComparator(partialName))
        .toList();

    return cityMapper.map(foundCities);
  }

  @Nonnull
  @Override
  public String getCityWithRegionByIdOrEmpty(@Nullable String cityId) {
    return isNotBlank(cityId)
        ? findCityWithRegionById(cityId)
          .orElse(StringUtils.EMPTY)
        : StringUtils.EMPTY;
  }

  @Nonnull
  @Override
  public Optional<String> findCityWithRegionById(@Nonnull String cityId) {
    return cityRepository.findById(cityId)
        .map(city -> city.getTitle() + ", " + city.getRegion());
  }

  @Override
  public boolean isCityExistById(@Nonnull String cityId) {
    return cityRepository.existsById(cityId);
  }
}
