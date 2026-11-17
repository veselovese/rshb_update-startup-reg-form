package ru.rshb.intech.digital.statistics.agrobit.salary.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    schema = "digital_statistics",
    name = "agrobit_salary_position"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "externalId", "name"})
public class SalaryPosition {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "external_id",
      nullable = false,
      unique = true
  )
  private String externalId;

  @Column(
      name = "title",
      nullable = false
  )
  private String name;

  @ManyToMany(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      fetch = FetchType.LAZY,
      mappedBy = "positions"
  )
  private Set<SalaryPositionCategory> categories = new HashSet<>();

  @Column(
      name = "enabled",
      nullable = false
  )
  private Boolean enabled;

}
