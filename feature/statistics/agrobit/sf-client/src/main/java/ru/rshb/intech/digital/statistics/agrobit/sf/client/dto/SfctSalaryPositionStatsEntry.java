package ru.rshb.intech.digital.statistics.agrobit.sf.client.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SfctSalaryPositionStatsEntry {

  private LocalDate reportDate;

  private String category;

  private String position;

  private Long indicatorValue;

  private Long indicatorDiff;

  private Long indicatorDiffPct;
}
