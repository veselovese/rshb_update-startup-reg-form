package ru.rshb.intech.digital.feature.statistics.agrobit.university.service;

import ru.rshb.intech.digital.feature.statistics.agrobit.university.dto.EduTypeStatsDto;

import jakarta.annotation.Nonnull;
import java.util.List;

public interface AgrobitUniversityStatsService {

  @Nonnull
  List<EduTypeStatsDto> getUniversityStats();

}
