package ru.rshb.intech.digital.statistics.agrobit.tourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.statistics.agrobit.tourism.entity.TourismCategoryStatsEntity;

import jakarta.annotation.Nonnull;
import java.util.List;

public interface TourismCategoryStatsRepository extends JpaRepository<TourismCategoryStatsEntity, Long> {

  @Nonnull
  List<TourismCategoryStatsEntity> findAllByStatsTypeAndTourismTypeIgnoreCaseOrderByReportDateAsc(@Nonnull String statsType,
                                                                                                  @Nonnull String tourismType);

  @Nonnull
  List<TourismCategoryStatsEntity> findAllByStatsTypeAndRecreationTypeInOrderByRecreationType(@Nonnull String statsType,
                                                                                              @Nonnull List<String> recreationTypes);

}
