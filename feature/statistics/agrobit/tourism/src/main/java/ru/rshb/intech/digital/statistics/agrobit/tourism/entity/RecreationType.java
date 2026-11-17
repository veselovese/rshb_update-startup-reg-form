package ru.rshb.intech.digital.statistics.agrobit.tourism.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    name = "agrobit_tourism_recreation_type"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "externalId", "title"})
public class RecreationType {

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

  @JoinColumn(
      name = "type_id"
  )
  @ManyToOne(
      fetch = FetchType.LAZY
  )
  private TourismType tourismType;

  @OneToMany(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      fetch = FetchType.EAGER,
      mappedBy = "recreationType"
  )
  private List<TourismCategory> categories;

}
