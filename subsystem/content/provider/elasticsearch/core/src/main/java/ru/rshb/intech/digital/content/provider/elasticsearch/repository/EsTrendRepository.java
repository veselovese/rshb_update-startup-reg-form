package ru.rshb.intech.digital.content.provider.elasticsearch.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.TrendDto;

import java.util.Optional;

public interface EsTrendRepository extends ElasticsearchRepository<TrendDto, String> {

  @Nonnull
  Optional<TrendDto> findByExternalId(@Nonnull String externalId);

  boolean existsByExternalId(@Nonnull String externalId);
}
