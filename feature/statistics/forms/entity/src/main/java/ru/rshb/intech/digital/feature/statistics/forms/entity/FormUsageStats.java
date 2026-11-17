package ru.rshb.intech.digital.feature.statistics.forms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    schema = "digital_statistics",
    name = "o_plt_form_usage_stats"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class FormUsageStats {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "form_type",
      nullable = false
  )
  private String formTitle;

  @ManyToOne(optional = false)
  @JoinColumn(name = "report_id")
  private FormStatsReport report;

  @Column(
      name = "count",
      nullable = false
  )
  private Long usagesCount;

  @Column(
      name = "delta",
      nullable = false
  )
  private Long deltaUsagesCountForPeriod;

  @Column(
      name = "total",
      nullable = false
  )
  private Long totalUsagesCount;

  @Transient
  private String oldestRecordDate;
}
