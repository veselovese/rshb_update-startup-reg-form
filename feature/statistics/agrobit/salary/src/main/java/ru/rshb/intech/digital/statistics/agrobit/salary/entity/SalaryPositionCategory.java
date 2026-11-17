package ru.rshb.intech.digital.statistics.agrobit.salary.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
    name = "agrobit_salary_position_category"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "externalId", "name"})
public class SalaryPositionCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "external_id",
      nullable = false,
      unique = true,
      length = 100
  )
  private String externalId;

  @Column(
      name = "title",
      nullable = false
  )
  private String name;

  @Column(
      name = "enabled",
      nullable = false
  )
  private Boolean enabled;

  @ManyToMany(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      fetch = FetchType.EAGER
  )
  @JoinTable(
      schema = "digital_statistics",
      name = "agrobit_salary_pos_to_cat",
      joinColumns = @JoinColumn(name = "cat_id"),
      inverseJoinColumns = @JoinColumn(name = "pos_id")
  )
  private final Set<SalaryPosition> positions = new HashSet<>();

  public void addPosition(@Nonnull SalaryPosition position) {
    this.positions.add(position);
  }

}
