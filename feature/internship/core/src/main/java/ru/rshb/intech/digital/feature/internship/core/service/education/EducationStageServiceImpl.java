package ru.rshb.intech.digital.feature.internship.core.service.education;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.internship.core.dto.EducationStageRefDto;
import ru.rshb.intech.digital.feature.internship.core.entity.EducationStage;
import ru.rshb.intech.digital.feature.internship.core.mapper.EducationStageMapper;
import ru.rshb.intech.digital.feature.internship.core.repository.education.EducationStageRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationStageServiceImpl implements EducationStageService {

  private final EducationStageRepository educationStageRepository;
  private final EducationStageMapper educationStageMapper;

  @Nonnull
  @Override
  public List<EducationStageRefDto> findEnabledStages() {
    final var enabledStages = educationStageRepository.findAllByEnabledIsTrue();
    return educationStageMapper.map(enabledStages);
  }

  @Nonnull
  @Override
  public Optional<EducationStage> findByTitle(@Nonnull String title) {
    return educationStageRepository.findByTitleIgnoreCase(title);
  }
}
