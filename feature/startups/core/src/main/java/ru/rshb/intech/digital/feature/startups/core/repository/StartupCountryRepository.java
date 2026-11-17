package ru.rshb.intech.digital.feature.startups.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.startups.entity.StartupCountry;

import java.util.List;

public interface StartupCountryRepository extends JpaRepository<StartupCountry, Long> {


  @Nonnull
  List<StartupCountry> findAllByOrderById();
}
