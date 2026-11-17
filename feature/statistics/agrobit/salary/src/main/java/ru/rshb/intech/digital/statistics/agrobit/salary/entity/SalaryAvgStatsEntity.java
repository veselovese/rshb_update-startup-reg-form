package ru.rshb.intech.digital.statistics.agrobit.salary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(
    schema = "digital_statistics",
    name = "agrobit_salary_avg"
)
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
public class SalaryAvgStatsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "report_dt",
      nullable = false
  )
  private LocalDate reportDate;

  @Column(
      name = "cat_name",
      nullable = false
  )
  private String category;

  @Column(
      name = "pos_name"
  )
  private String position;

  @Column(
      name = "indicator_val",
      nullable = false
  )
  private Long value;

  @Column(
      name = "indicator_diff",
      nullable = false
  )
  private Long diff;

  @Column(
      name = "indicator_diff_pct",
      nullable = false
  )
  private Long diffPct;
}
