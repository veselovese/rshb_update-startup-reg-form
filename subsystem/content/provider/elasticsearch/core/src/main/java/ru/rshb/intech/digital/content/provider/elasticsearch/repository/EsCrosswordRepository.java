package ru.rshb.intech.digital.content.provider.elasticsearch.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.CrosswordDto;

import java.util.Optional;

public interface EsCrosswordRepository extends ElasticsearchRepository<CrosswordDto, String> {

  @Nonnull
  Optional<CrosswordDto> findByExternalId(@Nonnull String externalId);

  boolean existsByExternalId(@Nonnull String externalId);
}
