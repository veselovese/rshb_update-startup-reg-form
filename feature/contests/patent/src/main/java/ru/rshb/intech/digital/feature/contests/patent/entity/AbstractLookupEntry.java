package ru.rshb.intech.digital.feature.contests.patent.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@MappedSuperclass
public abstract class AbstractLookupEntry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "external_id",
      nullable = false
  )
  private String externalId;

  @Column(
      name = "title",
      nullable = false
  )
  private String value;

  @Column(
      name = "disabled",
      nullable = false
  )
  private Boolean disabled = false;

}
