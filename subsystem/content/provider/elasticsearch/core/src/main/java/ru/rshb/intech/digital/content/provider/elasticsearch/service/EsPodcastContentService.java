package ru.rshb.intech.digital.content.provider.elasticsearch.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.content.model.Podcast;
import ru.rshb.intech.digital.content.provider.api.PodcastContentService;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.PodcastDto;
import ru.rshb.intech.digital.content.provider.elasticsearch.mapper.PodcastModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.repository.EsPodcastRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class EsPodcastContentService implements PodcastContentService {

  private final EsPodcastRepository repository;
  private final PodcastModelMapper modelMapper;
  private final EsCircuitBreakerFactory circuitBreakerFactory;


  @Nonnull
  @Override
  public List<Podcast> findByDatePeriod(@Nonnull LocalDate periodStart,
                                        @Nonnull LocalDate periodEnd) { // TODO optimize
    return findAll()
        .stream()
        .filter(new PeriodFilter(periodStart, periodEnd))
        .toList();
  }

  @Nonnull
  private List<Podcast> findAll() {

    final var podcasts = circuitBreakerFactory.circuitBreaker()
        .run(
            () -> repository.findAll(),
            throwable -> {
              log.error("Podcasts retrieve error", throwable);
              return Collections.<PodcastDto>emptyList();
            }
        );

    return StreamSupport.stream(podcasts.spliterator(), false)
        .map(podcastDto -> modelMapper.mapFromDto(podcastDto))
        .toList();
  }

  @Nonnull
  @Override
  public Optional<Podcast> findById(@Nonnull String id) { // TODO optimize
    return findAll()
        .stream()
        .filter(p -> StringUtils.equals(p.getId(), id))
        .findAny();
  }

  @Override
  public boolean isExistById(@Nonnull String id) {
    return findById(id).isPresent();
  }

  private record PeriodFilter(
      LocalDate periodStartDate,
      LocalDate periodEndDate
  ) implements Predicate<Podcast> { // TODO optimize

    @Override
    public boolean test(Podcast podcast) {
      final var pubDate = podcast.getPublishDate();
      return (pubDate != null)
          && pubDate.isAfter(periodStartDate) && pubDate.isBefore(periodEndDate);
    }
  }
}
