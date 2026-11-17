package ru.rshb.intech.digital.publications.core.repository.views;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.rshb.intech.digital.publications.entity.PostView;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.Optional;

public interface PostViewsRepository extends JpaRepository<PostView, String> {

  @Nonnull
  Optional<PostView> findByPublicationId(@Nonnull String publicationId);

  @Nullable
  PostView getByPublicationId(@Nonnull String publicationId);

  @Modifying
  @Query(
      """
      update PostView pv
      set pv.viewedCount = pv.viewedCount + 1
      where pv.publicationId = :publicationId
      """
  )
  void incrementViewsByPublicationId(@Nonnull String publicationId);
}
