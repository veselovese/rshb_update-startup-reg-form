package ru.rshb.intech.digital.content.provider.elasticsearch.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.EventDto;

import java.util.Optional;

public interface EsEventRepository extends ElasticsearchRepository<EventDto, String> {

  @Nonnull
  Optional<EventDto> findByExternalId(@Nonnull String externalId);
}
