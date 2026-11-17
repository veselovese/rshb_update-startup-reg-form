package ru.rshb.intech.digital.content.provider.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.TechnologyDto;

import jakarta.annotation.Nonnull;
import java.util.Optional;

public interface EsTechnologyRepository extends ElasticsearchRepository<TechnologyDto, String> {

  @Nonnull
  Optional<TechnologyDto> findByExternalId(@Nonnull String externalId);

  boolean existsByExternalId(@Nonnull String externalId);
}
