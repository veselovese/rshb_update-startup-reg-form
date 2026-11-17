package ru.rshb.intech.digital.content.provider.elasticsearch.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.content.model.Infographic;
import ru.rshb.intech.digital.content.provider.api.InfographicsContentService;
import ru.rshb.intech.digital.content.provider.elasticsearch.mapper.InfographicsModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.repository.EsInfographicsRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EsInfographicContentService implements InfographicsContentService {

  private final EsInfographicsRepository repository;
  private final InfographicsModelMapper modelMapper;
  private final EsCircuitBreakerFactory circuitBreakerFactory;

  @Nonnull
  @Override
  public Optional<Infographic> findById(@Nonnull String id) {
    return circuitBreakerFactory.circuitBreaker()
        .run(
            () -> repository.findByExternalId(id)
                .map(dto -> modelMapper.mapFromDto(dto)),
            throwable -> {
              log.error("Infographic [{}] retrieve error", id, throwable);
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
              log.error("Infographic [{}] existence check error", id, throwable);
              return false;
            }
        );
  }
}
