package ru.rshb.intech.digital.feature.statistics.agrobit.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.entity.University;

import jakarta.annotation.Nonnull;
import java.util.Optional;

public interface AgrobitStatsUniversityRepository extends JpaRepository<University, Long> {

  @Nonnull
  Optional<University> findByNameIgnoreCase(@Nonnull String universityName);

}
