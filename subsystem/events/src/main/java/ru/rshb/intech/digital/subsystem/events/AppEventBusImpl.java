package ru.rshb.intech.digital.subsystem.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.subsystem.events.log.LogEvent;

import jakarta.annotation.Nonnull;

@Service
@RequiredArgsConstructor
public class AppEventBusImpl implements AppEventBus {

  private final ApplicationEventPublisher applicationEventPublisher;


  @Override
  public void publish(@Nonnull AppEvent event) {
    applicationEventPublisher.publishEvent(event);
  }

  @Override
  public void log(@Nonnull LogEvent event) {
    applicationEventPublisher.publishEvent(event);
  }
}
