package ru.rshb.intech.digital.feature.statistics.forms.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(
    schema = "digital_statistics",
    name = "o_plt_form_usage_reports"
)
@Getter
@Setter
@Builder
@Generated
@EqualsAndHashCode
@AllArgsConstructor
public class FormStatsReport {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "created_date",
      nullable = false
  )
  private LocalDateTime createdDate;

  @Column(
      name = "period_start_date",
      nullable = false
  )
  private LocalDateTime periodStartDate;

  @Column(
      name = "period_end_date",
      nullable = false
  )
  private LocalDateTime periodEndDate;

  @OneToMany(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      fetch = FetchType.EAGER,
      mappedBy = "report"
  )
  @Setter(AccessLevel.PRIVATE)
  private List<FormUsageStats> formStats;


  public FormStatsReport() {
    this.formStats = new ArrayList<>();
  }


  public void addStats(@Nonnull FormUsageStats stats) {
    formStats.add(stats);
    stats.setReport(this);
  }

  public void addStats(@Nonnull Collection<FormUsageStats> stats) {
    stats.forEach(this::addStats);
  }


  @Transient
  public Optional<FormUsageStats> findStatsByFormTitle(@Nonnull String formTitle) {
    if (CollectionUtils.isEmpty(formStats)) {
      return Optional.empty();
    }
    return formStats.stream()
        .filter(stat -> Objects.equals(stat.getFormTitle(), formTitle))
        .findFirst();
  }
}
