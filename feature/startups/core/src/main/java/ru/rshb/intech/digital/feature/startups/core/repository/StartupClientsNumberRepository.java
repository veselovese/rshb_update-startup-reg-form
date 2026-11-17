package ru.rshb.intech.digital.feature.startups.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.startups.entity.StartupClientsNumberVariant;

import java.util.List;
import java.util.Optional;

public interface StartupClientsNumberRepository extends JpaRepository<StartupClientsNumberVariant, Long> {

  boolean existsByExternalIdIgnoreCase(@Nonnull String externalId);

  @Nonnull
  Optional<StartupClientsNumberVariant> findByExternalIdIgnoreCase(@Nonnull String externalId);

  @Nonnull
  List<StartupClientsNumberVariant> findAllByDisabledIsFalseOrderById();
}
