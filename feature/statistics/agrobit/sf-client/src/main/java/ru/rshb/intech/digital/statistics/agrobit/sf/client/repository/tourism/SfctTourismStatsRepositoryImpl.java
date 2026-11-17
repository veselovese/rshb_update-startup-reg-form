package ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Repository;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.tourism.TourismAvgStatsEntry;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.tourism.TourismStatsEntry;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism.queries.TourismAvgStatsByTypeQuery;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism.queries.TourismAvgStatsParams;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism.queries.TourismAvgStatsQuery;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism.queries.TourismStatsByTypeParams;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism.queries.TourismStatsQuery;

import java.util.List;

@Repository
public class SfctTourismStatsRepositoryImpl implements SfctTourismStatsRepository {

  private final TourismStatsQuery tourismStatsQuery;
  private final TourismAvgStatsQuery tourismAvgStatsQuery;
  private final TourismAvgStatsByTypeQuery tourismAvgStatsByTypeQuery;


  public SfctTourismStatsRepositoryImpl(@Nonnull TourismStatsQuery tourismStatsQuery,
                                        @Nonnull TourismAvgStatsQuery tourismAvgStatsQuery,
                                        @Nonnull TourismAvgStatsByTypeQuery tourismAvgStatsByTypeQuery) {
    this.tourismStatsQuery = tourismStatsQuery;
    this.tourismAvgStatsQuery = tourismAvgStatsQuery;
    this.tourismAvgStatsByTypeQuery = tourismAvgStatsByTypeQuery;
  }

  @Nonnull
  @Override
  public List<TourismStatsEntry> find(@Nonnull TourismStatsByTypeParams searchParams) {
    return tourismStatsQuery.executeWith(searchParams);
  }

  @Nonnull
  @Override
  public List<TourismAvgStatsEntry> findAvg(@Nonnull TourismAvgStatsParams searchParams) {
    return tourismAvgStatsQuery.executeWith(searchParams);
  }

  @Nonnull
  @Override
  public List<TourismAvgStatsEntry> findAllByStatsType(@Nonnull TourismStatsType statsType) {
    return tourismAvgStatsByTypeQuery.executeWith(statsType);
  }
}
