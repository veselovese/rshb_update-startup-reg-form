package ru.rshb.intech.digital.feature.statistics.agrobit.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.entity.YearStatsDataItem;

public interface AgrobitYearStatsRepository extends JpaRepository<YearStatsDataItem, Long> {

  @Query(
    """
    select min(di.year)
    from YearStatsDataItem di
    """
  )
  Long findMinYear();
}
