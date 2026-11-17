package ru.rshb.intech.digital.vacancies.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.rshb.intech.digital.vacancies.entity.Employment;

import java.util.List;
import java.util.Set;

public interface EmploymentRepository extends JpaRepository<Employment, Long>, JpaSpecificationExecutor<Employment> {

  @Nonnull
  List<Employment> findAllByDeletedIsFalse();

  @Nonnull
  List<Employment> findAllByExternalIdInAndDeletedIsFalse(@Nonnull Set<String> externalIds);
}
