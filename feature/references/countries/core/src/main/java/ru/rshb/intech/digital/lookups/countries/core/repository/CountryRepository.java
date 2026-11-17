package ru.rshb.intech.digital.lookups.countries.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.lookups.countries.entity.Country;

import jakarta.annotation.Nonnull;

public interface CountryRepository extends JpaRepository<Country, Long> {

  boolean existsByShortTitleIgnoreCase(@Nonnull String country);
}
