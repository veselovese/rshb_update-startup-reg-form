package ru.rshb.intech.digital.content.provider.elasticsearch.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.content.model.Article;
import ru.rshb.intech.digital.content.provider.api.ArticleContentService;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.ArticleDto;
import ru.rshb.intech.digital.content.provider.elasticsearch.mapper.ArticleModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.repository.EsArticlesRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.ISO_EXTENDED_DATE;

@Slf4j
@Service
@RequiredArgsConstructor
public class EsArticleContentService implements ArticleContentService {

  private final EsArticlesRepository repository;
  private final ArticleModelMapper modelMapper;
  private final EsCircuitBreakerFactory circuitBreakerFactory;

  @Nonnull
  @Override
  public List<Article> findByDatePeriod(@Nonnull LocalDate periodStart, @Nonnull LocalDate periodEnd) {

    final var startDate = ISO_EXTENDED_DATE.format(periodStart);
    final var endDate = ISO_EXTENDED_DATE.format(periodEnd);

    final var articles = circuitBreakerFactory.circuitBreaker()
        .run(
            () -> repository.findAllByPublicationDateBetween(startDate, endDate),
            throwable -> {
              log.error("Articles list by period retrieve error", throwable);
              return Collections.<ArticleDto>emptyList();
            }
        );

    return modelMapper.mapFromDto(articles);
  }

  @Nonnull
  @Override
  public Optional<Article> findById(@Nonnull String id) {
    return circuitBreakerFactory.circuitBreaker()
        .run(
            () -> repository.findByExternalId(id)
                .map(dto -> modelMapper.mapFromDto(dto)),
            throwable -> {
              log.error("Article [{}] retrieve error", id, throwable);
              return Optional.empty();
            }
        );
  }

  @Override
  public boolean isExistById(@Nonnull String id) {
    return circuitBreakerFactory.circuitBreaker().run(
        () -> repository.existsByExternalId(id),
        throwable -> {
          log.error("Article [{}] existence check error", id, throwable);
          return false;
        }
    );
  }
}
