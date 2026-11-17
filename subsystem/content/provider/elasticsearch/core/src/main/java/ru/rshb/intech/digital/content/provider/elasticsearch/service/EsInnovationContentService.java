package ru.rshb.intech.digital.content.provider.elasticsearch.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.content.model.Innovation;
import ru.rshb.intech.digital.content.provider.api.InnovationContentService;
import ru.rshb.intech.digital.content.provider.elasticsearch.mapper.InnovationModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.repository.EsInnovationRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EsInnovationContentService implements InnovationContentService {

  private final EsInnovationRepository repository;
  private final InnovationModelMapper modelMapper;
  private final EsCircuitBreakerFactory circuitBreakerFactory;

  @Nonnull
  @Override
  public Optional<Innovation> findById(@Nonnull String id) {
    return circuitBreakerFactory.circuitBreaker()
        .run(
            () -> repository.findByExternalId(id)
                .map(dto -> modelMapper.mapFromDto(dto)),
            throwable -> {
              log.error("Innovation [{}] retrieve error", id, throwable);
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
              log.error("Innovation [{}] existence check error", id, throwable);
              return false;
            }
        );
  }
}
