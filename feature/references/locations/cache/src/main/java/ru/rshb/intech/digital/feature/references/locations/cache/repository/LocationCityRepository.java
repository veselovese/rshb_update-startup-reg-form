package ru.rshb.intech.digital.feature.references.locations.cache.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.references.locations.cache.entity.LocationCity;

import java.util.Optional;

public interface LocationCityRepository extends JpaRepository<LocationCity, Long> {

  boolean existsByExternalIdIgnoreCase(@Nonnull String externalId);

  @Nonnull
  Optional<LocationCity> findByExternalIdIgnoreCase(@Nonnull String externalId);
}
