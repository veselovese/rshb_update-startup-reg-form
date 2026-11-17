package ru.rshb.intech.digital.censor.dictionary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    name = "censor_dictionary"
)
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
public class WordEntity {

  @Id
  private Long id;

  @Column(
      name = "root",
      unique = true
  )
  private String root;

  @Column(
      name = "enabled",
      nullable = false
  )
  private Boolean enabled;
}
