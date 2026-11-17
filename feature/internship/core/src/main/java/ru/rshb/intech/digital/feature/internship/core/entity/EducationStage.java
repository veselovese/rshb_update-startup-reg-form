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

@Entity
@Table(
    schema = "digital_internship",
    name = "education_stage"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class EducationStage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "external_id",
      unique = true,
      length = 100,
      nullable = false
  )
  private String externalId;

  @Column(
      name = "title",
      length = 100,
      nullable = false,
      unique = true
  )
  private String title;

  @Column(name = "min_year")
  private Integer minYear;

  @Column(name = "max_year")
  private Integer maxYear;

  @Column(
      name = "enabled",
      nullable = false
  )
  private Boolean enabled;


  public boolean isWithoutYear() {
    return (minYear == null && maxYear == null);
  }
}
