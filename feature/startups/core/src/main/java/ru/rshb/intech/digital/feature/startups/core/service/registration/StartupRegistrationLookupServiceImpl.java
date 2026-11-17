package ru.rshb.intech.digital.feature.startups.core.service.registration;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.startups.core.repository.StartupCategoryRepository;
import ru.rshb.intech.digital.feature.startups.core.repository.StartupProjectStageRepository;
import ru.rshb.intech.digital.feature.startups.dto.LookupValueDto;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegLookupDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupCategory;
import ru.rshb.intech.digital.feature.startups.entity.StartupProjectStage;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StartupRegistrationLookupServiceImpl implements StartupRegistrationLookupService {

  private static final List<String> REVENUE_RANGES = List.of(
      "Нет",
      "До 1 млн",
      "1-10 млн",
      "10-50 млн",
      "50-100 млн",
      ">100 млн"
  );

  private static final List<LookupValueDto> AI_OPTIONS = List.of(
      new LookupValueDto("true", "Есть"),
      new LookupValueDto("false", "Нет")
  );

  private final StartupCategoryRepository categoryRepository;
  private final StartupProjectStageRepository projectStageRepository;

  @Nonnull
  @Override
  public StartupRegLookupDto getRegistrationLookups() {
    return new StartupRegLookupDto(
        categoryRepository.findAllByDisabledIsFalseOrderById().stream()
            .map(category -> new LookupValueDto(category.getExternalId(), category.getTitle()))
            .toList(),
        projectStageRepository.findAllByDisabledIsFalseOrderById().stream()
            .map(stage -> new LookupValueDto(stage.getExternalId(), stage.getTitle()))
            .toList(),
        REVENUE_RANGES,
        AI_OPTIONS
    );
  }

  @Override
  public boolean isCategoryExist(@Nonnull String externalId) {
    return findCategory(externalId).isPresent();
  }

  @Nonnull
  @Override
  public Optional<StartupCategory> findCategory(@Nonnull String externalId) {
    return categoryRepository.findByExternalIdIgnoreCaseAndDisabledIsFalse(externalId);
  }

  @Override
  public boolean isProjectStageExist(@Nonnull String externalId) {
    return findProjectStage(externalId).isPresent();
  }

  @Nonnull
  @Override
  public Optional<StartupProjectStage> findProjectStage(@Nonnull String externalId) {
    return projectStageRepository.findByExternalIdIgnoreCase(externalId)
        .filter(stage -> !Boolean.TRUE.equals(stage.getDisabled()));
  }
}
