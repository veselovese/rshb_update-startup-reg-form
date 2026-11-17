package ru.rshb.intech.digital.content.provider.elasticsearch.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.content.model.Crossword;
import ru.rshb.intech.digital.content.provider.api.CrosswordContentService;
import ru.rshb.intech.digital.content.provider.elasticsearch.mapper.CrosswordModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.repository.EsCrosswordRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EsCrosswordContentService implements CrosswordContentService {

  private final EsCrosswordRepository repository;
  private final CrosswordModelMapper modelMapper;
  private final EsCircuitBreakerFactory circuitBreakerFactory;

  @Nonnull
  @Override
  public List<Crossword> findAll() {
    return circuitBreakerFactory.circuitBreaker()
        .run(
            () -> IterableUtils.toList(repository.findAll())
                .stream()
                .map(dto -> modelMapper.mapFromDto(dto))
                .toList(),
            throwable -> {
              log.error("Crosswords retrieve error", throwable);
              return Collections.emptyList();
            }
        );
  }

  @Nonnull
  @Override
  public Optional<Crossword> findById(@Nonnull String id) {
    return circuitBreakerFactory.circuitBreaker()
        .run(
            () -> repository.findByExternalId(id)
                .map(dto -> modelMapper.mapFromDto(dto)),
            throwable -> {
              log.error("Crossword [{}] retrieve error", id, throwable);
              return Optional.empty();
            }
        );
  }

  @Override
  public boolean isExistsById(@Nonnull String id) {
    return circuitBreakerFactory.circuitBreaker()
        .run(
            () -> repository.existsByExternalId(id),
            throwable -> {
              log.error("Crossword [{}] existence check error", id, throwable);
              return false;
            }
        );
  }
}
