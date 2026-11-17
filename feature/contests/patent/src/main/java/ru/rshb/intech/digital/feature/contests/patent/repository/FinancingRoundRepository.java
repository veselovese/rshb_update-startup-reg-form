package ru.rshb.intech.digital.feature.contests.patent.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.contests.patent.entity.FinancingRoundOption;

import java.util.List;
import java.util.Optional;

public interface FinancingRoundRepository extends JpaRepository<FinancingRoundOption, Long> {

  @Nonnull
  Optional<FinancingRoundOption> findByExternalIdIgnoreCase(@Nonnull String externalId);

  @Nonnull
  List<FinancingRoundOption> findAllByDisabledIsFalse();
}
