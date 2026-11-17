package ru.rshb.intech.digital.vacancies.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.vacancies.entity.Specialization;

import java.util.List;
import java.util.Set;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

  @Nonnull
  List<Specialization> findAllByDeletedIsFalse();

  @Nonnull
  List<Specialization> findAllByExternalIdInAndDeletedIsFalse(@Nonnull Set<String> externalIds);
}
