package ru.rshb.intech.digital.subsystem.events;

import ru.rshb.intech.digital.subsystem.events.log.LogEvent;

import jakarta.annotation.Nonnull;

public interface AppEventBus {

  void publish(@Nonnull AppEvent event);

  void log(@Nonnull LogEvent event);

}
