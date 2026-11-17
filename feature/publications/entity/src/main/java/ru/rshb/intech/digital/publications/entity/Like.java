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
import java.time.LocalDateTime;

@Entity
@Table(
    schema = "digital_publications",
    name = "o_plt_post_likes"
)
@Getter
@Setter
@Builder
@Generated
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"sessionId", "publicationId"})
@IdClass(LikeId.class)
public class Like implements Serializable {

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

  @Enumerated(value = EnumType.STRING)
  private LikeState state;

  private LocalDateTime updated;
}
