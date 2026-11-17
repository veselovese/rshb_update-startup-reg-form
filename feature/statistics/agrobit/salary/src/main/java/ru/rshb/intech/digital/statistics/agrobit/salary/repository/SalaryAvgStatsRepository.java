package ru.rshb.intech.digital.statistics.agrobit.salary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.statistics.agrobit.salary.entity.SalaryAvgStatsEntity;

import jakarta.annotation.Nonnull;
import java.util.List;

public interface SalaryAvgStatsRepository extends JpaRepository<SalaryAvgStatsEntity, Long> {

  @Nonnull
  List<SalaryAvgStatsEntity> findAllByPositionInOrderByPosition(@Nonnull List<String> positions);

  @Nonnull
  List<SalaryAvgStatsEntity> findAllByCategoryIgnoreCase(@Nonnull String category);
}
