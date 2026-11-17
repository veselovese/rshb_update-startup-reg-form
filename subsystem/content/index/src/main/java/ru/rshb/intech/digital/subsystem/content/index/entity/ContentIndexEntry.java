package ru.rshb.intech.digital.subsystem.content.index.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rshb.intech.digital.content.model.ItemType;

@Entity
@Table(
    schema = "digital_publications",
    name = "publication_index"
)
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ContentIndexEntry {

  @Id
  private String id;

  @Column(
      name = "item_type",
      nullable = false
  )
  @Enumerated(EnumType.STRING)
  private ItemType itemType;

}
