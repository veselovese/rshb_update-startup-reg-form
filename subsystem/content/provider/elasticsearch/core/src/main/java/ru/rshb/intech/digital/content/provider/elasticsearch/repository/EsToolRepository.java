package ru.rshb.intech.digital.content.provider.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.ToolDto;

import jakarta.annotation.Nonnull;
import java.util.Optional;

public interface EsToolRepository extends ElasticsearchRepository<ToolDto, String> {

  @Nonnull
  Optional<ToolDto> findByExternalId(@Nonnull String externalId);

  boolean existsByExternalId(@Nonnull String externalId);
}
