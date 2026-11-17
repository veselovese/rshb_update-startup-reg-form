package ru.rshb.intech.digital.feature.internship.core.service.statistics;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.internship.core.dto.statistics.InternshipStatisticsDto;
import ru.rshb.intech.digital.feature.internship.core.entity.RegistrationMonthlyStats;

import java.util.List;

public interface InternshipStatisticsService {

  @Nonnull
  InternshipStatisticsDto getMonthlyRelativeStatistics(@Nonnull InternshipStatisticsFilter filter);

  @Nonnull
  List<RegistrationMonthlyStats> refreshMonthlyStats();
}
