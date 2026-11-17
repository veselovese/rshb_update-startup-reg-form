package ru.rshb.intech.digital.puzzles.core.farm;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.puzzles.dto.farm.CommonUsageStatsDto;

public interface DigitalFarmStatsService {

  @Nonnull
  CommonUsageStatsDto getCommonUsageStats();

}
