package ru.rshb.intech.digital.feature.startups.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.startups.entity.StartupCurrency;

import java.util.List;
import java.util.Optional;

public interface StartupCurrencyRepository extends JpaRepository<StartupCurrency, Long> {

  boolean existsByExternalIdIgnoreCase(@Nonnull String externalId);

  @Nonnull
  Optional<StartupCurrency> findByExternalIdIgnoreCase(@Nonnull String externalId);

  @Nonnull
  List<StartupCurrency> findAllByDisabledIsFalseOrderById();
}
