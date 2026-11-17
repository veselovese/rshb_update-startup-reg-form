package ru.rshb.intech.digital.content.provider.elasticsearch.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.content.model.Technology;
import ru.rshb.intech.digital.content.provider.api.TechnologyContentService;
import ru.rshb.intech.digital.content.provider.elasticsearch.mapper.TechnologyModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.repository.EsTechnologyRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EsTechnologyContentService implements TechnologyContentService {

  private final EsTechnologyRepository repository;
  private final TechnologyModelMapper modelMapper;
  private final EsCircuitBreakerFactory circuitBreakerFactory;

  @Nonnull
  @Override
  public Optional<Technology> findById(@Nonnull String id) {
    return circuitBreakerFactory.circuitBreaker()
        .run(
            () -> repository.findByExternalId(id)
                .map(dto -> modelMapper.mapFromDto(dto)),
            throwable -> {
              log.error("Technology [{}] retrieve error", id, throwable);
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
              log.error("Technology [{}] existence check error", id, throwable);
              return false;
            }
        );
  }
}
