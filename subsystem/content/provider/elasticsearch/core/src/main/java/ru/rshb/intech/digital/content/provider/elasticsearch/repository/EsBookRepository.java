package ru.rshb.intech.digital.content.provider.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.BookDto;

import jakarta.annotation.Nonnull;
import java.util.Optional;

public interface EsBookRepository extends ElasticsearchRepository<BookDto, String> {

  @Nonnull
  Optional<BookDto> findByExternalId(@Nonnull String externalId);

  boolean existsByExternalId(@Nonnull String externalId);
}
