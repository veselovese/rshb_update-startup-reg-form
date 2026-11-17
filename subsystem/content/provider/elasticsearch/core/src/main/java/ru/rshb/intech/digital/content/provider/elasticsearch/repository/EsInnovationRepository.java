package ru.rshb.intech.digital.content.provider.elasticsearch.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.InnovationDto;

import java.util.Optional;

public interface EsInnovationRepository extends ElasticsearchRepository<InnovationDto, String> {

  @Nonnull
  Optional<InnovationDto> findByExternalId(@Nonnull String externalId);

  boolean existsByExternalId(@Nonnull String externalId);
}
