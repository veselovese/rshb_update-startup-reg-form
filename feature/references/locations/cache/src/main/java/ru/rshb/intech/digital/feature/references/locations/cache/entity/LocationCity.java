package ru.rshb.intech.digital.feature.references.locations.cache.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    schema = "digital_refs",
    name = "location_city"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class LocationCity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "external_id")
  private String externalId;

  @ManyToOne(
      cascade = {
          CascadeType.MERGE,
          CascadeType.PERSIST
      },
      fetch = FetchType.EAGER
  )
  @JoinColumn(name = "country_id")
  private LocationCountry country;

  private String title;

  public LocationCity(@Nonnull LocationCountry country,
                      @Nonnull String externalId,
                      @Nonnull String title) {

    this.country = country;
    this.externalId = externalId;
    this.title = title;
  }
}
