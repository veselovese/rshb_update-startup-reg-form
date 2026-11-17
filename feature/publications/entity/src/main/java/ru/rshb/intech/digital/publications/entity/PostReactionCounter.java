package ru.rshb.intech.digital.publications.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rshb.intech.digital.publications.dto.LikeState;

import java.io.Serializable;

@Entity
@Table(
    schema = "digital_publications",
    name = "o_plt_post_reactions_ct"
)
@Getter
@Setter
@Builder
@Generated
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"publicationId", "reaction"})
@IdClass(PostReactionCounterId.class)
public class PostReactionCounter implements Serializable {

  @Id
  @Column(
      name = "publication_id",
      nullable = false
  )
  private String publicationId;

  @Id
  @Column(
      name = "reaction",
      nullable = false
  )
  @Enumerated(EnumType.STRING)
  private LikeState reaction;

  @Column(
      name = "reaction_count",
      nullable = false
  )
  private Long reactionCount;
}
