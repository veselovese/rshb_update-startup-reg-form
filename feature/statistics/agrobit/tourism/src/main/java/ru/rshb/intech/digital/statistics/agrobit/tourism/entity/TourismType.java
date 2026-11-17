package ru.rshb.intech.digital.statistics.agrobit.tourism.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(
    schema = "digital_statistics",
    name = "agrobit_tourism_type"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "externalId", "title"})
public class TourismType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "external_id",
      nullable = false,
      unique = true
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
  private Boolean enabled = false;

  @OneToMany(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      fetch = FetchType.EAGER,
      mappedBy = "tourismType"
  )
  private List<RecreationType> recreationTypes;

}
