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
public class TourismStatsEntry {

  private LocalDate reportDate;
  private String tourismType;
  private String recreationType;
  private Long indicatorValue;
  private Long indicatorDiff;
  private Long indicatorDiffPct;

}
