package ru.rshb.intech.digital.content.provider.elasticsearch.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.content.model.Trend;
import ru.rshb.intech.digital.content.provider.api.TrendContentService;
import ru.rshb.intech.digital.content.provider.elasticsearch.mapper.TrendModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.repository.EsTrendRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EsTrendContentService implements TrendContentService {

  private final EsTrendRepository repository;
  private final TrendModelMapper modelMapper;
  private final EsCircuitBreakerFactory circuitBreakerFactory;

  @Nonnull
  @Override
  public Optional<Trend> findById(@Nonnull String id) {
    return circuitBreakerFactory.circuitBreaker()
        .run(
            () -> repository.findByExternalId(id)
                .map(dto -> modelMapper.mapFromDto(dto)),
            throwable -> {
              log.error("Trend [{}] retrieve error", id, throwable);
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
              log.error("Trend [{}] existence check error", id, throwable);
              return false;
            }
        );
  }
}
