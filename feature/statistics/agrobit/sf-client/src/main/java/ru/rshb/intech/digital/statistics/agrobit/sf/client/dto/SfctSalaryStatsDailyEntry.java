package ru.rshb.intech.digital.statistics.agrobit.sf.client.dto;

import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
public class SfctSalaryStatsDailyEntry {

  private LocalDate reportDt;
  private String businessNm;
  private String kpiNm;
  private String aggTypeVal;
  private String positionNm;
  private String categoryNm;
  private String regionNm;
  private String kpiCnt;
  private LocalDateTime loadDttm;
}
