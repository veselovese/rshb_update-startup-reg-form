package ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism.queries;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.repository.tourism.TourismStatsType;

import java.util.Set;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TourismAvgStatsParams {

  private TourismStatsType statsType;
  private String tourismType;
  private Set<String> recreations;
}
