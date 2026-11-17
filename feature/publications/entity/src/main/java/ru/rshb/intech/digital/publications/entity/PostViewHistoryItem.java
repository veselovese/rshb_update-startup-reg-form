package ru.rshb.intech.digital.publications.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(
    schema = "digital_publications",
    name = "o_plt_post_view_history"
)
@Getter
@Setter
@Builder
@Generated
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"sessionId", "publicationId"})
@IdClass(PostViewHistoryItemId.class)
public class PostViewHistoryItem implements Serializable {

  @Id
  @Column(
      name = "session_id",
      nullable = false
  )
  private String sessionId;

  @Id
  @Column(
      name = "publication_id",
      nullable = false
  )
  private String publicationId;

  private LocalDateTime viewed;
}
