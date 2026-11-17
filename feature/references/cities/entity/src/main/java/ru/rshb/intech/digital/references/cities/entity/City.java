package ru.rshb.intech.digital.references.cities.entity;

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
    name = "okato_city"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class City {

  @Id
  private String id;

  @Column(
      name = "region",
      length = 300,
      nullable = false
  )
  private String region;

  @Column(
      name = "title",
      length = 300,
      nullable = false
  )
  private String title;
}
