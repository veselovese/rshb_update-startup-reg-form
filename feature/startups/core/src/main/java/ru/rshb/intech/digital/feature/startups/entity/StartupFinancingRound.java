package ru.rshb.intech.digital.feature.startups.entity;

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

@Entity
@Table(
    schema = "digital_startups",
    name = "reg_fin_round_ref"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class StartupFinancingRound {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "external_id")
  private String externalId;

  private String title;

  private Boolean disabled = false;
}
