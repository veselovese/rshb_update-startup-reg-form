package ru.rshb.intech.digital.references.cities.core.mapper;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.references.cities.dto.CityDto;
import ru.rshb.intech.digital.references.cities.entity.City;

import jakarta.annotation.Nonnull;
import java.util.List;

@Component
public class CityMapper {

  @Nonnull
  public CityDto map(@Nonnull City city) {
    final var dto = new CityDto();
    dto.setId(city.getId());
    dto.setTitle(city.getTitle());
    dto.setRegion(city.getRegion());
    return dto;
  }

  @Nonnull
  public List<CityDto> map(@Nonnull List<City> cities) {
    return cities.stream()
        .map(this::map)
        .toList();
  }
}
