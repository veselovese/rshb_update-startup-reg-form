package ru.rshb.intech.digital.statistics.agrobit.salary.service.sfct;

import ru.rshb.intech.digital.statistics.agrobit.commons.PlotBundleDto;
import ru.rshb.intech.digital.statistics.agrobit.salary.dto.SalaryStatsFilter;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public interface SfctSalaryStatisticsService {

  @Nonnull
  PlotBundleDto getAvgSalaryStatistics(@Nonnull SalaryStatsFilter filter);

  @Nonnull
  PlotBundleDto getAvgSalaryStatsByCategories(@Nullable String fields);

}
