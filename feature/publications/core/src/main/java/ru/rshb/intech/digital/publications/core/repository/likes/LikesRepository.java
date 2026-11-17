package ru.rshb.intech.digital.publications.core.repository.likes;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.publications.entity.Like;
import ru.rshb.intech.digital.publications.entity.LikeId;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Like, LikeId> {

  @Nonnull
  Optional<Like> findLikeByPublicationIdAndSessionId(@Nonnull String publicationId, @Nonnull String sessionId);
}
