package ru.rshb.intech.digital.feature.references.locations.cache.entity;

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
    schema = "digital_refs",
    name = "location_country"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class LocationCountry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "external_id")
  private String externalId;

  private String title;

  public LocationCountry(@Nonnull String externalId, @Nonnull String title) {
    this.externalId = externalId;
    this.title = title;
  }
}
