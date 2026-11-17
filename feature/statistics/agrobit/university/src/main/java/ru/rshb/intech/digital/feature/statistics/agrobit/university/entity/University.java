package ru.rshb.intech.digital.feature.statistics.agrobit.university.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "agrobit_university")
@Table(
    schema = "digital_statistics",
    name = "agrobit_university"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class University {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "uni_name",
      nullable = false
  )
  private String name;

  @Column(name = "uni_short_name")
  private String shortName;

  @Column(
      name = "disabled",
      nullable = false
  )
  private Boolean disabled = false;

  @OneToMany(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      mappedBy = "university",
      fetch = FetchType.EAGER
  )
  private List<Faculty> faculties;
}
