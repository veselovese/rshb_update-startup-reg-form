package ru.rshb.intech.digital.statistics.agrobit.salary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.statistics.agrobit.salary.entity.SalaryPositionCategory;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public interface SalaryPositionCategoryRepository extends JpaRepository<SalaryPositionCategory, Long> {

  @Nonnull
  List<SalaryPositionCategory> findAllByEnabledTrueOrderByNameAsc();

  @Nonnull
  Optional<SalaryPositionCategory> findByExternalIdIgnoreCaseAndEnabledTrue(@Nonnull String externalId);

  @Nonnull
  Optional<SalaryPositionCategory> findByNameIgnoreCase(@Nonnull String categoryName);

  @Nonnull
  List<SalaryPositionCategory> findAllByNameInAndEnabledTrue(@Nonnull List<String> categoryNames);
}
