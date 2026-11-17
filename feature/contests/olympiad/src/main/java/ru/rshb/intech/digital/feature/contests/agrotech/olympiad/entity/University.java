package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity;

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

@Entity(name = "AgrotechOlympiadRegUniversity")
@Table(
    schema = "digital_contests",
    name = "agrotech_olympiad_university_ref"
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

  @Column(name = "external_id")
  private String externalId;

  @Column(name = "full_title")
  private String fullTitle;

  @Column(name = "short_title")
  private String shortTitle;

  @Column(name = "top_index")
  private Integer topIndex;

  private Boolean disabled = false;
}
