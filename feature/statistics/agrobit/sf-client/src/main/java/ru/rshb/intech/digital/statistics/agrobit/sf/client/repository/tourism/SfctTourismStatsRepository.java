package ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.tourism.TourismAvgStatsEntry;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.tourism.TourismStatsEntry;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism.queries.TourismAvgStatsParams;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism.queries.TourismStatsByTypeParams;

import java.util.List;

public interface SfctTourismStatsRepository {

  @Nonnull
  List<TourismStatsEntry> find(@Nonnull TourismStatsByTypeParams searchParams);

  @Nonnull
  List<TourismAvgStatsEntry> findAvg(@Nonnull TourismAvgStatsParams searchParams);

  @Nonnull
  List<TourismAvgStatsEntry> findAllByStatsType(@Nonnull TourismStatsType statsType);
}
