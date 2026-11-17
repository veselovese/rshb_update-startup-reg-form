package ru.rshb.intech.digital.feature.internship.core.service.education;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.feature.internship.core.dto.EducationStageRefDto;
import ru.rshb.intech.digital.feature.internship.core.entity.EducationStage;
import ru.rshb.intech.digital.feature.internship.core.mapper.EducationStageMapper;
import ru.rshb.intech.digital.feature.internship.core.repository.education.EducationStageRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EducationStageServiceTest {

  @Mock
  private EducationStageRepository educationStageRepository;

  private EducationStageServiceImpl educationStageService;

  private EducationStage educationStage;

  @BeforeEach
  void setUp() {
    educationStage = new EducationStage();

    educationStageService = new EducationStageServiceImpl(
        educationStageRepository,
        new EducationStageMapper()
    );
  }

  @Test
  void findEnabledStages() {

    given(
        educationStageRepository.findAllByEnabledIsTrue()
    ).willReturn(List.of(educationStage));

    final List<EducationStageRefDto> result = educationStageService.findEnabledStages();

    assertThat(result)
        .isNotNull();

    assertThat(result.size())
        .isEqualTo(1);
  }

  @Test
  void findByTitle() {
    given(
        educationStageRepository.findByTitleIgnoreCase(anyString())
    ).willReturn(Optional.of(educationStage));

    final Optional<EducationStage> result = educationStageService.findByTitle("abracadabra");

    assertThat(
        result.isPresent()
    ).isTrue();
  }
}