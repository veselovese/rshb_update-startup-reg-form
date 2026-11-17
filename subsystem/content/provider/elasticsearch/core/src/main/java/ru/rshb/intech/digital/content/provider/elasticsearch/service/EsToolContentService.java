package ru.rshb.intech.digital.content.provider.elasticsearch.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.content.model.Tool;
import ru.rshb.intech.digital.content.provider.api.ToolContentService;
import ru.rshb.intech.digital.content.provider.elasticsearch.mapper.ToolModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.repository.EsToolRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EsToolContentService implements ToolContentService {

  private final EsToolRepository repository;
  private final ToolModelMapper modelMapper;
  private final EsCircuitBreakerFactory circuitBreakerFactory;

  @Nonnull
  @Override
  public Optional<Tool> findById(@Nonnull String id) {
    return circuitBreakerFactory.circuitBreaker()
        .run(
            () -> repository.findByExternalId(id)
                .map(dto -> modelMapper.mapFromDto(dto)),
            throwable -> {
              log.error("Tool [{}] retrieve error", id, throwable);
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
              log.error("Tool [{}] existence check error", id, throwable);
              return false;
            }
        );
  }
}
