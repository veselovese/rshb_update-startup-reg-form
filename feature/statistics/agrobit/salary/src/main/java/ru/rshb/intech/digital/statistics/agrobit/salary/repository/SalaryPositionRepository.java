package ru.rshb.intech.digital.statistics.agrobit.salary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.statistics.agrobit.salary.entity.SalaryPosition;

import jakarta.annotation.Nonnull;
import java.util.List;

public interface SalaryPositionRepository extends JpaRepository<SalaryPosition, Long> {

  @Nonnull
  List<SalaryPosition> findAllByExternalIdInAndEnabledTrueOrderByNameAsc(@Nonnull List<String> externalIds);

}
