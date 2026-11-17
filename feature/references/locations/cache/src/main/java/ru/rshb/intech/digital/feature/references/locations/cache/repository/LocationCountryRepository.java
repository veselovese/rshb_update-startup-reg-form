package ru.rshb.intech.digital.feature.references.locations.cache.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.references.locations.cache.entity.LocationCountry;

import java.util.List;
import java.util.Optional;

public interface LocationCountryRepository extends JpaRepository<LocationCountry, Long> {

  @Nonnull
  List<LocationCountry> findAllByTitleContainingIgnoreCase(@Nonnull String title);

  @Nonnull
  Optional<LocationCountry> findByExternalIdIgnoreCase(@Nonnull String externalId);

  boolean existsByExternalIdIgnoreCase(@Nonnull String externalId);
}
