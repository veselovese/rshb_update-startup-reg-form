package ru.rshb.intech.digital.vacancies.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.rshb.intech.digital.vacancies.entity.Vacancy;

import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy, Long>, JpaSpecificationExecutor<Vacancy> {

  @Nonnull
  Optional<Vacancy> findByExternalId(@Nonnull String externalId);
}
