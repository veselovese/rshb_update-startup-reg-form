package ru.rshb.intech.digital.feature.internship.core.entity;

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

import java.time.LocalDateTime;

@Entity
@Table(
    schema = "digital_internship",
    name = "registration_stats_monthly"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class RegistrationMonthlyStats {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "date",
      nullable = false
  )
  private LocalDateTime date;

  @Column(
      name = "field",
      nullable = false
  )
  private String field;

  @Column(
      name = "amount",
      nullable = false
  )
  private Long amount;

  @Column(
      name = "diff",
      nullable = false
  )
  private Double diff;
}
