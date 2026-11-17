package ru.rshb.intech.digital.publications.core.service.publication;

import ru.rshb.intech.digital.publications.dto.LikeDto;
import ru.rshb.intech.digital.publications.dto.PublicationSummaryInfo;
import ru.rshb.intech.digital.publications.entity.Like;

import jakarta.annotation.Nonnull;
import java.util.List;

public interface PublicationService {

  @Nonnull
  List<PublicationSummaryInfo> getPublicationsSummaries(@Nonnull List<String> publicationIds,
                                                        @Nonnull String sessionId,
                                                        boolean applyIncrement);

  @Nonnull
  PublicationSummaryInfo getPublicationSummary(@Nonnull String publicationId,
                                               @Nonnull String sessionId,
                                               boolean applyIncrement);

  @Nonnull
  LikeDto.Response setLikeToPublication(@Nonnull Like like);


  @Nonnull
  List<PublicationSummaryInfo> getPodcastSummaries(@Nonnull List<String> podcastIds, @Nonnull String sessionId);

  @Nonnull
  List<PublicationSummaryInfo> getInnovationDemandSummaries(@Nonnull List<String> demandIds, @Nonnull String sessionId);

  @Nonnull
  PublicationSummaryInfo updatePodcastListeningCount(@Nonnull String publicationId, @Nonnull String sessionId);

  @Nonnull
  PublicationSummaryInfo updateInnovationDemandViewCount(@Nonnull String demandId, @Nonnull String sessionId);

  @Nonnull
  List<PublicationSummaryInfo> getInfographicsSummaries(@Nonnull List<String> infographicIds, @Nonnull String sessionId);

  @Nonnull
  PublicationSummaryInfo updateInfographicViewCount(@Nonnull String infographicId, @Nonnull String sessionId);
}
