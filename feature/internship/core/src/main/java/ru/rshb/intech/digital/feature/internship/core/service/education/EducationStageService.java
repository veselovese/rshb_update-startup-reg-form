package ru.rshb.intech.digital.feature.internship.core.service.education;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.internship.core.dto.EducationStageRefDto;
import ru.rshb.intech.digital.feature.internship.core.entity.EducationStage;

import java.util.List;
import java.util.Optional;

public interface EducationStageService {

  @Nonnull
  List<EducationStageRefDto> findEnabledStages();

  @Nonnull
  Optional<EducationStage> findByTitle(@Nonnull String title);
}
