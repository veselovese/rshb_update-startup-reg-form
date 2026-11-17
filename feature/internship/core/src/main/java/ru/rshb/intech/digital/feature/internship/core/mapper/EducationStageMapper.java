package ru.rshb.intech.digital.feature.internship.core.mapper;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.internship.core.dto.EducationStageRefDto;
import ru.rshb.intech.digital.feature.internship.core.entity.EducationStage;

import java.util.List;

@Component
public class EducationStageMapper {

  @Nonnull
  public EducationStageRefDto map(@Nonnull EducationStage entity) {
    final var dto = new EducationStageRefDto();
    dto.setId(entity.getExternalId());
    dto.setTitle(entity.getTitle());
    dto.setMinYear(entity.getMinYear());
    dto.setMaxYear(entity.getMaxYear());
    return dto;
  }

  @Nonnull
  public List<EducationStageRefDto> map(@Nonnull List<EducationStage> entities) {
    return entities.stream()
        .map(this::map)
        .toList();
  }
}
