package ru.rshb.intech.digital.references.universities.core.mapper;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.references.universities.dto.UniversityDto;
import ru.rshb.intech.digital.references.universities.entity.University;

import jakarta.annotation.Nonnull;
import java.util.List;

@Component
public class UniversityMapper {

  @Nonnull
  public UniversityDto map(@Nonnull University entity) {
    final var dto = new UniversityDto();
    dto.setId(entity.getExternalId());
    dto.setFullName(entity.getFullTitle());
    dto.setShortName(entity.getShortTitle());
    return dto;
  }

  @Nonnull
  public List<UniversityDto> map(@Nonnull List<University> entities) {
    return entities.stream()
        .map(this::map)
        .toList();
  }
}
