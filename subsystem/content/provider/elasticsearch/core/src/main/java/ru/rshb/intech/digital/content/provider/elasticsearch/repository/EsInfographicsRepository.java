package ru.rshb.intech.digital.content.provider.elasticsearch.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.InfographicDto;

import java.util.Optional;

public interface EsInfographicsRepository extends ElasticsearchRepository<InfographicDto, String> {

  @Nonnull
  Optional<InfographicDto> findByExternalId(@Nonnull String externalId);

  boolean existsByExternalId(@Nonnull String externalId);
}
