package ru.rshb.intech.digital.feature.internship.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    schema = "digital_internship",
    name = "o_plt_internship_field"
)
@Getter
@Setter
@Generated
@EqualsAndHashCode(of = "id")
public class InternshipField {

  @Id
  private Long id;

  private String key;

  private String title;

  private Boolean enabled;

  private Boolean deleted;
}
