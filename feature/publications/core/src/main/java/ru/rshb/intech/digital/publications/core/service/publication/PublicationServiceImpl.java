package ru.rshb.intech.digital.publications.core.service.publication;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.content.provider.api.GeneralPublicationService;
import ru.rshb.intech.digital.content.provider.api.InfographicsContentService;
import ru.rshb.intech.digital.content.provider.api.InnovationContentService;
import ru.rshb.intech.digital.content.provider.api.PodcastContentService;
import ru.rshb.intech.digital.publications.core.exception.PublicationsException;
import ru.rshb.intech.digital.publications.core.service.likes.LikesService;
import ru.rshb.intech.digital.publications.core.service.views.PublicationViewsService;
import ru.rshb.intech.digital.publications.dto.LikeDto;
import ru.rshb.intech.digital.publications.dto.LikeState;
import ru.rshb.intech.digital.publications.dto.PublicationSummaryInfo;
import ru.rshb.intech.digital.publications.entity.Like;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

@Service
@Transactional
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

  private static final long DEFAULT_UNLIKED_NUMBER = 0L;

  private final LikesService likesService;
  private final GeneralPublicationService publicationService;
  private final PublicationViewsService publicationViewsService;
  private final PodcastContentService podcastContentService;
  private final InnovationContentService innovationContentService;
  private final InfographicsContentService infographicsContentService;


  @Nonnull
  @Override
  public List<PublicationSummaryInfo> getPublicationsSummaries(@Nonnull List<String> publicationIds,
                                                               @Nonnull String sessionId,
                                                               boolean applyIncrement) {

    final var nonEmptyPubIds = emptyIfNull(publicationIds)
        .stream()
        .filter(StringUtils::isNotBlank)
        .collect(Collectors.toUnmodifiableSet());

    if (CollectionUtils.isEmpty(nonEmptyPubIds)) {
      return Collections.emptyList();
    }

    final var publicationInfo = new ArrayList<PublicationSummaryInfo>();

    for (final String publicationId : nonEmptyPubIds) {

      final var publication = publicationService.getById(publicationId);
      if (publication == null) {
        continue;
      }

      final Long likesCount = likesService.getLikesCount(publication);
      final Like like       = likesService.getLikeState(publication, sessionId);
      final Long viewsCount = (applyIncrement)
          ? publicationViewsService.incrementViewsCount(publication, sessionId)
          : publicationViewsService.getViewsCount(publication, sessionId);

      publicationInfo.add(
          PublicationSummaryInfo.builder()
              .publicationId(publicationId)
              .views(viewsCount)
              .likes(likesCount)
              .state(like.getState())
              .build()
      );
    }

    return publicationInfo;
  }

  @Nonnull
  @Override
  public PublicationSummaryInfo getPublicationSummary(@Nonnull String publicationId,
                                                      @Nonnull String sessionId,
                                                      boolean applyIncrement) {

    if (StringUtils.isBlank(publicationId)) {
      throw new PublicationsException("Publication ID is not specified");
    }

    final var publication = publicationService.getById(publicationId);
    if (publication == null) {
      return PublicationSummaryInfo.empty(publicationId);
    }

    final Long likesCount = likesService.getLikesCount(publication);
    final Like like       = likesService.getLikeState(publication, sessionId);
    final Long viewsCount = (applyIncrement)
        ? publicationViewsService.incrementViewsCount(publication, sessionId)
        : publicationViewsService.getViewsCount(publication, sessionId);

    return PublicationSummaryInfo.builder()
        .publicationId(publicationId)
        .views(viewsCount)
        .likes(likesCount)
        .state(like.getState())
        .build();
  }

  @Nonnull
  @Override
  public LikeDto.Response setLikeToPublication(@Nonnull Like reaction) {

    final var publication = publicationService.getById(reaction.getPublicationId());
    if (publication == null) {
      return new LikeDto.Response(DEFAULT_UNLIKED_NUMBER, LikeState.UNLIKED);
    }

    likesService.setLikeToPublication(reaction);
    var likesCount = likesService.getLikesCount(publication);

    return new LikeDto.Response(likesCount, reaction.getState());
  }

  @Nonnull
  @Override
  public List<PublicationSummaryInfo> getPodcastSummaries(@Nonnull List<String> podcastIds, @Nonnull String sessionId) {
    final var summaries = new ArrayList<PublicationSummaryInfo>();
    for (final var podcastId : podcastIds) {
      final var podcast = podcastContentService.getOrNull(podcastId);
      if (podcast == null) {
        summaries.add(PublicationSummaryInfo.empty(podcastId));
        continue;
      }
      final var summary = getPublicationSummary(podcastId, sessionId, false);
      summaries.add(summary);
    }
    return summaries;
  }

  @Nonnull
  @Override
  public PublicationSummaryInfo updatePodcastListeningCount(@Nonnull String publicationId, @Nonnull String sessionId) {
    final var podcast = podcastContentService.getOrNull(publicationId);
    return (podcast != null)
        ? getPublicationSummary(publicationId, sessionId, true)
        : PublicationSummaryInfo.empty(publicationId);
  }

  @Nonnull
  @Override
  public List<PublicationSummaryInfo> getInnovationDemandSummaries(@Nonnull List<String> innovationIds, @Nonnull String sessionId) {
    return innovationIds.stream()
        .map(innovationId -> innovationContentService.getOrNull(innovationId))
        .filter(Objects::nonNull)
        .map(demand -> getPublicationSummary(demand.getId(), sessionId, false))
        .toList();
  }

  @Nonnull
  @Override
  public PublicationSummaryInfo updateInnovationDemandViewCount(@Nonnull String innovationId, @Nonnull String sessionId) {
    return innovationContentService.findById(innovationId)
        .map(demand -> getPublicationSummary(innovationId, sessionId, true))
        .orElseGet(() -> PublicationSummaryInfo.empty(innovationId));
  }

  @Nonnull
  @Override
  public List<PublicationSummaryInfo> getInfographicsSummaries(@Nonnull List<String> infographicIds, @Nonnull String sessionId) {
    final var summaries = new ArrayList<PublicationSummaryInfo>();
    for (final var infographicId : infographicIds) {
      final var infographic = infographicsContentService.getOrNull(infographicId);
      final var summary = (infographic != null)
          ? getPublicationSummary(infographicId, sessionId, false)
          : PublicationSummaryInfo.empty(infographicId);
      summaries.add(summary);
    }
    return summaries;
  }

  @Nonnull
  @Override
  public PublicationSummaryInfo updateInfographicViewCount(@Nonnull String infographicId, @Nonnull String sessionId) {
    return infographicsContentService.findById(infographicId)
        .map(infographic -> getPublicationSummary(infographicId, sessionId, true))
        .orElseGet(() -> PublicationSummaryInfo.empty(infographicId));
  }
}
