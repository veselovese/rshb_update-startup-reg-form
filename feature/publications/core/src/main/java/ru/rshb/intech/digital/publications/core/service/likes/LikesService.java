package ru.rshb.intech.digital.publications.core.service.likes;


import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.content.model.Publication;
import ru.rshb.intech.digital.publications.entity.Like;

public interface LikesService {

  @Nonnull
  Long getLikesCount(@Nonnull Publication publication);

  void setLikeToPublication(@Nonnull Like like);

  @Nonnull
  Like getLikeState(@Nonnull Publication publication, @Nonnull String sessionId);
}
