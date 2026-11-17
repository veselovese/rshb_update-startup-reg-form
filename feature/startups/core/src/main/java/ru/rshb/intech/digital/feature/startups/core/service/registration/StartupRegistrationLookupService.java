package ru.rshb.intech.digital.feature.startups.core.service.registration;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegLookupDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupCategory;
import ru.rshb.intech.digital.feature.startups.entity.StartupProjectStage;

import java.util.Optional;

public interface StartupRegistrationLookupService {

  @Nonnull
  StartupRegLookupDto getRegistrationLookups();

  boolean isCategoryExist(@Nonnull String externalId);

  @Nonnull
  Optional<StartupCategory> findCategory(@Nonnull String externalId);

  boolean isProjectStageExist(@Nonnull String externalId);

  @Nonnull
  Optional<StartupProjectStage> findProjectStage(@Nonnull String externalId);

}
