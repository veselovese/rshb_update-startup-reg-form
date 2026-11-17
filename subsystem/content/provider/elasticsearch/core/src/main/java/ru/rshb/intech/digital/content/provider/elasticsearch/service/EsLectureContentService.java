package ru.rshb.intech.digital.content.provider.elasticsearch.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.content.model.Lecture;
import ru.rshb.intech.digital.content.provider.api.LectureContentService;
import ru.rshb.intech.digital.content.provider.elasticsearch.mapper.LecutreModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.repository.EsLectureRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EsLectureContentService implements LectureContentService {

  private final EsLectureRepository repository;
  private final LecutreModelMapper modelMapper;
  private final EsCircuitBreakerFactory circuitBreakerFactory;

  @Nonnull
  @Override
  public Optional<Lecture> findById(@Nonnull String id) {
    return circuitBreakerFactory.circuitBreaker()
        .run(
            () -> repository.findByExternalId(id)
                .map(dto -> modelMapper.mapFromDto(dto)),
            throwable -> {
              log.error("Lecture [{}] retrieve error", id, throwable);
              return Optional.empty();
            }
        );
  }

  @Override
  public boolean isExistById(@Nonnull String id) {
    return circuitBreakerFactory.circuitBreaker()
        .run(
            () -> repository.existsByExternalId(id),
            throwable -> {
              log.error("Lecture [{}] existence check error", id, throwable);
              return false;
            }
        );
  }
}
