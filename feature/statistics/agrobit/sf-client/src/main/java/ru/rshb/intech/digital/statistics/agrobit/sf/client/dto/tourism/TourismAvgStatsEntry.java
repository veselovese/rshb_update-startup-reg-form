package ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.tourism;

import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
public class TourismAvgStatsEntry {

  private LocalDate reportDate;
  private String tourismType;
  private Long indicatorValue;
  private Long indicatorDiff;
  private Long indicatorDiffPct;
}
