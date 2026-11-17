package ru.rshb.intech.digital.statistics.agrobit.tourism.repository;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rshb.intech.digital.statistics.agrobit.commons.ValueDiffStat;
import ru.rshb.intech.digital.statistics.agrobit.tourism.repository.query.StatsByTypeAndAllRecreationTypesQuery;
import ru.rshb.intech.digital.statistics.agrobit.tourism.repository.query.StatsByTypeAndAllTourismTypesQuery;
import ru.rshb.intech.digital.statistics.agrobit.tourism.repository.query.StatsByTypeAndRecreationTypesAndSubCatQuery;
import ru.rshb.intech.digital.statistics.agrobit.tourism.repository.query.StatsByTypeAndRecreationTypesQuery;
import ru.rshb.intech.digital.statistics.agrobit.tourism.repository.query.StatsByTypeAndTourismTypeQuery;

import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class TourismStatsRepositoryImpl implements TourismStatsRepository {

  private final StatsByTypeAndTourismTypeQuery statsByTypeAndTourismTypeQuery;
  private final StatsByTypeAndAllTourismTypesQuery statsByTypeAndAllTourismTypesQuery;
  private final StatsByTypeAndRecreationTypesQuery statsByTypeAndRecreationTypesQuery;
  private final StatsByTypeAndRecreationTypesAndSubCatQuery statsByTypeAndRecreationTypesAndSubCatQuery;
  private final StatsByTypeAndAllRecreationTypesQuery statsByTypeAndAllRecreationTypesQuery;

  @Nonnull
  @Override
  public List<ValueDiffStat> findAllByStatsTypeAndTourismType(@Nonnull String statsType, @Nonnull String tourismType) {
    return statsByTypeAndTourismTypeQuery.executeWith(statsType, tourismType);
  }

  @Nonnull
  @Override
  public List<ValueDiffStat> findAllByStatsTypeAndAllTourismTypes(@Nonnull String statsType, @Nonnull String subCatTitle) {
    return statsByTypeAndAllTourismTypesQuery.executeWith(statsType, subCatTitle);
  }

  @Nonnull
  @Override
  public List<ValueDiffStat> findAllByStatsTypeAndRecreationTypes(@Nonnull String statsType, @Nonnull Set<String> recreationTypes) {
    return statsByTypeAndRecreationTypesQuery.executeWith(statsType, recreationTypes);
  }

  @Nonnull
  @Override
  public List<ValueDiffStat> findAllByStatsTypeAndRecreationTypes(@Nonnull String statsType,
                                                                  @Nonnull Set<String> recreationTypes,
                                                                  @Nonnull String subCatTitle) {

    return statsByTypeAndRecreationTypesAndSubCatQuery.executeWith(statsType, recreationTypes, subCatTitle);
  }

  @Nonnull
  @Override
  public List<ValueDiffStat> findAllByStatsTypeAndAllRecreationTypes(@Nonnull String statsType, @Nonnull String subCatTitle) {
    return statsByTypeAndAllRecreationTypesQuery.executeWith(statsType, subCatTitle);
  }
}
