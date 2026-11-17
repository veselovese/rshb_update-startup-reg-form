package ru.rshb.intech.digital.feature.startups.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.startups.entity.StartupCategory;

import java.util.List;
import java.util.Optional;

public interface StartupCategoryRepository extends JpaRepository<StartupCategory, Long> {

  boolean existsByExternalIdIgnoreCase(@Nonnull String externalId);

  @Nonnull
  Optional<StartupCategory> findByExternalIdIgnoreCaseAndDisabledIsFalse(@Nonnull String externalId);

  @Nonnull
  List<StartupCategory> findAllByDisabledIsFalse();

  @Nonnull
  List<StartupCategory> findAllByDisabledIsFalseOrderById();
}
