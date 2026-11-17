package ru.rshb.intech.digital.content.provider.elasticsearch.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.content.model.Book;
import ru.rshb.intech.digital.content.provider.api.BooksContentService;
import ru.rshb.intech.digital.content.provider.elasticsearch.mapper.BookModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.repository.EsBookRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EsBookContentService implements BooksContentService {

  private final EsBookRepository repository;
  private final BookModelMapper modelMapper;
  private final EsCircuitBreakerFactory circuitBreakerFactory;

  @Nonnull
  @Override
  public Optional<Book> findById(@Nonnull String id) {
    return circuitBreakerFactory.circuitBreaker()
        .run(
            () -> repository.findByExternalId(id)
                .map(dto -> modelMapper.mapFromDto(dto)),
            throwable -> {
              log.error("Book [{}] retrieve error", id, throwable);
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
              log.error("Book [{}] existence check error", id, throwable);
              return false;
            }
        );
  }
}
