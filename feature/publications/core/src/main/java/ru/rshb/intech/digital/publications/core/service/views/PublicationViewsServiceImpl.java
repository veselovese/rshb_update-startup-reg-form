package ru.rshb.intech.digital.publications.core.service.views;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rshb.intech.digital.content.model.Publication;
import ru.rshb.intech.digital.publications.core.repository.views.PostViewHistoryRepository;
import ru.rshb.intech.digital.publications.core.repository.views.PostViewsRepository;
import ru.rshb.intech.digital.publications.entity.PostView;
import ru.rshb.intech.digital.publications.entity.PostViewHistoryItem;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PublicationViewsServiceImpl implements PublicationViewsService {

  private final PostViewsRepository postViewsRepository;
  private final PostViewHistoryRepository postViewHistoryRepository;


  @Nonnull
  @Override
  @Transactional
  public Long incrementViewsCount(@Nonnull Publication publication, @Nonnull String sessionId) {

    makeHistoryRecord(publication.getId(), sessionId);

    final var viewCounter = postViewsRepository.getByPublicationId(publication.getId());

    return (viewCounter != null)
        ? incrementViews(viewCounter)
        : saveDefaultViewsCount(publication);
  }

  private void makeHistoryRecord(@Nonnull String publicationId, @Nonnull String sessionId) {
    postViewHistoryRepository.save(
        PostViewHistoryItem.builder()
            .viewed(LocalDateTime.now())
            .publicationId(publicationId)
            .sessionId(sessionId)
            .build()
    );
  }

  private long incrementViews(@Nonnull PostView view) {
    postViewsRepository.incrementViewsByPublicationId(view.getPublicationId());
    return view.getViewedCount() + 1;
  }

  private long saveDefaultViewsCount(@Nonnull Publication publication) {
    final long defaultViewsCount = publication.getViews();
    postViewsRepository.save(new PostView(publication.getId(), defaultViewsCount));
    return defaultViewsCount;
  }

  @Nonnull
  @Override
  @Transactional
  public Long getViewsCount(@Nonnull Publication publication, @Nonnull String sessionId) {

    makeHistoryRecord(publication.getId(), sessionId);

    final var viewCounter = postViewsRepository.getByPublicationId(publication.getId());

    return (viewCounter != null)
        ? viewCounter.getViewedCount()
        : saveDefaultViewsCount(publication);
  }
}
