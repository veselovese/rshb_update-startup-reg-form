package ru.rshb.intech.digital.feature.internship.core.entity;

import jakarta.annotation.Nonnull;
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
    name = "pref_hrs_wk_ref"
)
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
public class WorkHours {

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
  private String title;

  @Column(
      name = "enabled",
      nullable = false
  )
  private Boolean enabled = true;

  public WorkHours(@Nonnull Long id, @Nonnull String externalId, @Nonnull String title) {
    setId(id);
    setExternalId(externalId);
    setTitle(title);
  }
}
