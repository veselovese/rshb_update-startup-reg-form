package ru.rshb.intech.digital.publications.core.repository.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.publications.dto.LikeState;
import ru.rshb.intech.digital.publications.entity.PostReactionCounter;
import ru.rshb.intech.digital.publications.entity.PostReactionCounterId;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public interface PostReactionCounterRepository extends JpaRepository<PostReactionCounter, PostReactionCounterId> {

  @Nonnull
  Optional<PostReactionCounter> findByPublicationIdAndReaction(@Nonnull String publicationId, @Nonnull LikeState reaction);

  @Nonnull
  List<PostReactionCounter> findByPublicationId(String publicationId);
}
