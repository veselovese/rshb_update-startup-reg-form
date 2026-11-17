package ru.rshb.intech.digital.statistics.agrobit.tourism.repository;

import ru.rshb.intech.digital.statistics.agrobit.commons.ValueDiffStat;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Set;

public interface TourismStatsRepository {

  @Nonnull
  List<ValueDiffStat> findAllByStatsTypeAndTourismType(@Nonnull String statsType, @Nonnull String tourismType);

  @Nonnull
  List<ValueDiffStat> findAllByStatsTypeAndAllTourismTypes(@Nonnull String statsType, @Nonnull String subCatTitle);

  @Nonnull
  List<ValueDiffStat> findAllByStatsTypeAndRecreationTypes(@Nonnull String statsType, @Nonnull Set<String> recreationTypes);

  @Nonnull
  List<ValueDiffStat> findAllByStatsTypeAndRecreationTypes(@Nonnull String statsType, @Nonnull Set<String> recreationTypes, @Nonnull String subCatTitle);

  @Nonnull
  List<ValueDiffStat> findAllByStatsTypeAndAllRecreationTypes(@Nonnull String statsType, @Nonnull String subCatTitle);

}
