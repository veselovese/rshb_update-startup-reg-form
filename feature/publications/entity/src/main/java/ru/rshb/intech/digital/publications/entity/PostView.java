package ru.rshb.intech.digital.publications.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    schema = "digital_publications",
    name = "o_plt_post_view_ct"
)
@Getter
@Setter
@Builder
@Generated
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"publicationId"})
public class PostView {

  @Id
  @Column(
      name = "publication_id",
      nullable = false
  )
  private String publicationId;

  @Column(
      name = "view_count",
      nullable = false
  )
  public Long viewedCount;
}
