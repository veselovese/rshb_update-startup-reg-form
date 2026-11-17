package ru.rshb.intech.digital.publications.core.service.likes;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rshb.intech.digital.content.model.Publication;
import ru.rshb.intech.digital.publications.core.repository.likes.LikesRepository;
import ru.rshb.intech.digital.publications.core.repository.likes.PostReactionCounterRepository;
import ru.rshb.intech.digital.publications.dto.LikeState;
import ru.rshb.intech.digital.publications.entity.Like;
import ru.rshb.intech.digital.publications.entity.PostReactionCounter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService {

  private static final long DEFAULT_LIKES_NUMBER = 0L;
  private static final long DEFAULT_UNLIKES_NUMBER = 0L;

  private final LikesRepository likesRepository;
  private final PostReactionCounterRepository reactionCounterRepository;


  @Nonnull
  @Override
  public Long getLikesCount(@Nonnull Publication publication) {

    final var reactionCounters = reactionCounterRepository.findByPublicationId(publication.getId());
    if (reactionCounters.isEmpty()) {
      return saveDefaultLikes(publication);
    }

    final var counterMap = reactionCounters.stream()
        .collect(Collectors.toMap(c -> c.getReaction(), c -> c.getReactionCount()));

    final long likedCount = defaultIfNull(counterMap.get(LikeState.LIKED), DEFAULT_LIKES_NUMBER);
    final long unlikedCount = defaultIfNull(counterMap.get(LikeState.UNLIKED), DEFAULT_UNLIKES_NUMBER);

    final long likesCount = likedCount - unlikedCount;

    return (likesCount >= 0) ? likesCount : DEFAULT_LIKES_NUMBER;
  }

  private long saveDefaultLikes(@Nonnull Publication publication) {

    final var reactionCounters = List.of(
        new PostReactionCounter(publication.getId(), LikeState.LIKED, publication.getLikes()),
        new PostReactionCounter(publication.getId(), LikeState.UNLIKED, DEFAULT_UNLIKES_NUMBER)
    );

    reactionCounterRepository.saveAll(reactionCounters);

    return publication.getLikes();
  }


  @Override
  @Transactional
  public void setLikeToPublication(@Nonnull Like reactionData) {

    // 1. Ищем запись в лайках
    Like likeForCurrentUser = likesRepository.findLikeByPublicationIdAndSessionId(
        reactionData.getPublicationId(),
        reactionData.getSessionId()
    ).orElse(null);

    if (likeForCurrentUser == null && reactionData.getState() == LikeState.UNLIKED) { // Защита от дурака - когда первым запросом приходит установка Unlike
      return;
    }

    // 2. Еcли она есть:
    if (likeForCurrentUser != null) {

      // 3. Если она есть и состояние совпадает с полученным - ничего не делаем
      if (likeForCurrentUser.getState() == reactionData.getState()) {
        return;
      }

      // 4. Если она есть и состояние не совпадает с полученным - меняем на полученное и увеличиваем счетчик
      likeForCurrentUser.setState(reactionData.getState());
      updateReactionState(likeForCurrentUser);

      updateReactionCounter(likeForCurrentUser);
      return;
    }

    // 5. Если ее нет - создаем запись о лайке и увеличиваем счетчик;
    likeForCurrentUser = likesRepository.saveAndFlush(reactionData);
    updateReactionCounter(likeForCurrentUser);
  }

  private void updateReactionCounter(@Nonnull Like reaction) {

    final var counter = reactionCounterRepository.findByPublicationIdAndReaction(
            reaction.getPublicationId(),
            reaction.getState()
        )
        .orElse(
            new PostReactionCounter(
                reaction.getPublicationId(),
                reaction.getState(),
                0L
            )
        );

    counter.setReactionCount(counter.getReactionCount() + 1);
    reactionCounterRepository.saveAndFlush(counter);
  }

  private void updateReactionState(@Nonnull Like reaction) {

    Like like = likesRepository.findLikeByPublicationIdAndSessionId(
            reaction.getPublicationId(),
            reaction.getSessionId()
        )
        .orElse(reaction);

    like.setState(reaction.getState());
    like.setUpdated(LocalDateTime.now());

    likesRepository.saveAndFlush(like);
  }

  @Nonnull
  @Override
  public Like getLikeState(@Nonnull Publication publication, @Nonnull String sessionId) {

    final var publicationId = publication.getId();

    return likesRepository.findLikeByPublicationIdAndSessionId(publicationId, sessionId)
        .orElseGet(
            () -> Like.builder()
                .publicationId(publicationId)
                .sessionId(sessionId)
                .updated(LocalDateTime.now())
                .state(LikeState.UNLIKED)
                .build()
        );
  }
}
