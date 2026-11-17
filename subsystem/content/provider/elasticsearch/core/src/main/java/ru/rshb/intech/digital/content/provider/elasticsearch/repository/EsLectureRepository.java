package ru.rshb.intech.digital.content.provider.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.LectureDto;

import jakarta.annotation.Nonnull;
import java.util.Optional;

public interface EsLectureRepository extends ElasticsearchRepository<LectureDto, String> {

  @Nonnull
  Optional<LectureDto> findByExternalId(@Nonnull String externalId);

  boolean existsByExternalId(@Nonnull String externalId);
}
