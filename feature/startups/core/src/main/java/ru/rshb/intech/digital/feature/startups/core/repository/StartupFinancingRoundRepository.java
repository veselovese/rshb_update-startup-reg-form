package ru.rshb.intech.digital.feature.startups.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.startups.entity.StartupFinancingRound;

import java.util.List;
import java.util.Optional;

public interface StartupFinancingRoundRepository extends JpaRepository<StartupFinancingRound, Long> {

  boolean existsByExternalIdIgnoreCase(@Nonnull String externalId);

  @Nonnull
  Optional<StartupFinancingRound> findByExternalIdIgnoreCase(@Nonnull String externalId);

  @Nonnull
  List<StartupFinancingRound> findAllByDisabledIsFalseOrderById();
}
