package ru.rshb.intech.digital.content.provider.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.PodcastDto;

import jakarta.annotation.Nonnull;
import java.util.Optional;

public interface EsPodcastRepository extends ElasticsearchRepository<PodcastDto, String> {

  @Nonnull
  Optional<PodcastDto> findByExternalId(@Nonnull String externalId);
}
