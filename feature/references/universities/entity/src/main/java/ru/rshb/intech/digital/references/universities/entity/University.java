package ru.rshb.intech.digital.references.universities.entity;

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
    schema = "digital_refs",
    name = "university"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class University {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "external_id",
      length = 100,
      unique = true,
      nullable = false
  )
  private String externalId;

  @Column(
      name = "full_title",
      length = 300,
      nullable = false
  )
  private String fullTitle;

  @Column(
      name = "short_title",
      length = 100,
      nullable = true
  )
  private String shortTitle;
}
