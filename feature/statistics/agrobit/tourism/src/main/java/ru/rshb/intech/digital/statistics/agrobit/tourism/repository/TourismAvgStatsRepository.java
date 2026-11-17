package ru.rshb.intech.digital.statistics.agrobit.tourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.statistics.agrobit.tourism.entity.TourismAvgStatsEntity;

import jakarta.annotation.Nonnull;
import java.util.List;

public interface TourismAvgStatsRepository extends JpaRepository<TourismAvgStatsEntity, Long> {

  @Nonnull
  List<TourismAvgStatsEntity> findAllByStatsTypeAndTourismTypeOrderByStatsType(@Nonnull String statsType, @Nonnull String tourismType);

}
