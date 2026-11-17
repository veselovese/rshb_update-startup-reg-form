package ru.rshb.intech.digital.content.provider.elasticsearch.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.content.model.Event;
import ru.rshb.intech.digital.content.provider.api.EventContentService;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.EventDto;
import ru.rshb.intech.digital.content.provider.elasticsearch.mapper.EventModelMapper;
import ru.rshb.intech.digital.content.provider.elasticsearch.repository.EsEventRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Slf4j
@Service
@RequiredArgsConstructor
public class EsEventContentService implements EventContentService {

  private final EsEventRepository repository;
  private final EventModelMapper modelMapper;
  private final EsCircuitBreakerFactory circuitBreakerFactory;

  @Nonnull
  @Override
  public Optional<Event> findById(@Nonnull String id) {
    return circuitBreakerFactory.circuitBreaker()
        .run(
            () -> repository.findByExternalId(id)
                .map(dto -> modelMapper.mapFromDto(dto)),
            throwable -> {
              log.error("Event [{}] retrieve error", id, throwable);
              return Optional.empty();
            }
        );
  }

  @Nonnull
  @Override
  public List<Event> findActual() {
    final var events = circuitBreakerFactory.circuitBreaker()
        .run(
            () -> repository.findAll(),
            throwable -> {
              log.error("Actual events retrieve error", throwable);
              return Collections.<EventDto>emptyList();
            }
        );
    return IterableUtils.toList(events) // TODO оптимизировать
        .stream()
        .filter(new ActualEventFilter())
        .map(dto -> modelMapper.mapFromDto(dto))
        .toList();
  }

  @Slf4j
  @RequiredArgsConstructor
  private static class ActualEventFilter implements Predicate<EventDto> {

    /**
     * Данные о событии могут содержать как обе даты периода события (дата начала и дата завершения),
     * так и либо только дату начала, либо дату завершения.
     * <p>
     * Если указана только дата начала - данное событие однодневное.
     *
     * @param event the input argument
     * @return Возвращает <code>true</code>, если проверяемое событие является актуальным.
     */
    @Override
    public boolean test(EventDto event) {

      final var today = LocalDate.now();

      if (hasNoPeriod(event)) {
        log.warn("Event [{}] has no boundary dates", event.getSlug());
        return false;
      }

      if (hasFullPeriod(event)) {
        return isEqualOrAfter(today, event.getStartDate())
            && isEqualOrBefore(today, event.getEndDate());
      }

      if (isSingleDay(event)) {
        return today.isEqual(event.getStartDate());
      }

      return isEqualOrBefore(today, event.getEndDate());
    }

    private boolean hasNoPeriod(@Nonnull EventDto event) {
      return (event.getStartDate() == null && event.getEndDate() == null);
    }

    private boolean hasFullPeriod(@Nonnull EventDto event) {
      return (event.getStartDate() != null && event.getEndDate() != null);
    }

    private boolean isSingleDay(@Nonnull EventDto event) {
      return (event.getStartDate() != null && event.getEndDate() == null);
    }

    private boolean isEqualOrBefore(@Nonnull LocalDate date1, @Nonnull LocalDate date2) {
      return date1.isEqual(date2) || date1.isBefore(date2);
    }

    private boolean isEqualOrAfter(@Nonnull LocalDate date1, @Nonnull LocalDate date2) {
      return date1.isEqual(date2) || date1.isAfter(date2);
    }
  }
}
