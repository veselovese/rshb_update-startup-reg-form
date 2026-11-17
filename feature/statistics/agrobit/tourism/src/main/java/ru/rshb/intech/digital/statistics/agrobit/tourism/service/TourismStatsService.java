package ru.rshb.intech.digital.statistics.agrobit.tourism.service;

import ru.rshb.intech.digital.statistics.agrobit.commons.PlotBundleDto;
import ru.rshb.intech.digital.statistics.agrobit.tourism.entity.TourismStatsFilter;

import jakarta.annotation.Nonnull;

public interface TourismStatsService {

  @Nonnull
  PlotBundleDto getCheckStats(@Nonnull TourismStatsFilter filter);

  @Nonnull
  PlotBundleDto getCheckStatsAvg();

  @Nonnull
  PlotBundleDto getPriceStats(@Nonnull TourismStatsFilter filter);

  @Nonnull
  PlotBundleDto getPriceStatsAvg();

  @Nonnull
  PlotBundleDto getTourNumberStats(@Nonnull TourismStatsFilter filter);

  @Nonnull
  PlotBundleDto getTourNumberStatsAvg();

}
