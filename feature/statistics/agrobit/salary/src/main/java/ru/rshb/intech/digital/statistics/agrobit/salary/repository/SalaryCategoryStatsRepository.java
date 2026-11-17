package ru.rshb.intech.digital.statistics.agrobit.salary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.statistics.agrobit.salary.entity.SalaryCategoryStatsEntity;

import jakarta.annotation.Nonnull;
import java.util.List;

public interface SalaryCategoryStatsRepository extends JpaRepository<SalaryCategoryStatsEntity, Long> {

  @Nonnull
  List<SalaryCategoryStatsEntity> findAllByCategoryIgnoreCaseOrderByReportDate(@Nonnull String category);

  @Nonnull
  List<SalaryCategoryStatsEntity> findAllByOrderByReportDate();

}
