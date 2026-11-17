package ru.rshb.intech.digital.vacancies.core.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.vacancies.entity.Company;

import java.util.Collection;
import java.util.List;

public interface HHCompanyRepository extends JpaRepository<Company, Long> {

  @Nonnull
  List<Company> findAllByDeletedIsFalse();

  @Nonnull
  List<Company> findAllByDeletedIsFalseAndExternalIdIn(@Nonnull Collection<String> ids);
}
