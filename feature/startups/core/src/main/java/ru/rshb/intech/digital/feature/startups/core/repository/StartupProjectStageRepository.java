package ru.rshb.intech.digital.feature.startups.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.startups.entity.StartupProjectStage;

import java.util.List;
import java.util.Optional;

public interface StartupProjectStageRepository extends JpaRepository<StartupProjectStage, Long> {

  boolean existsByExternalIdIgnoreCase(@Nonnull String externalId);

  @Nonnull
  Optional<StartupProjectStage> findByExternalIdIgnoreCase(@Nonnull String externalId);

  @Nonnull
  List<StartupProjectStage> findAllByDisabledIsFalseOrderById();
}
