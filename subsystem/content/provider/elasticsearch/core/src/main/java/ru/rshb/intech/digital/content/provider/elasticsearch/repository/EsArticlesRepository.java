package ru.rshb.intech.digital.content.provider.elasticsearch.repository;


import jakarta.annotation.Nonnull;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.ArticleDto;

import java.util.List;
import java.util.Optional;

public interface EsArticlesRepository extends ElasticsearchRepository<ArticleDto, String> {

  @Nonnull
  Optional<ArticleDto> findByExternalId(@Nonnull String externalId);

  boolean existsByExternalId(@Nonnull String externalId);

  @Nonnull
  List<ArticleDto> findAllByPublicationDateBetween(String startDate, String endDate);

}
