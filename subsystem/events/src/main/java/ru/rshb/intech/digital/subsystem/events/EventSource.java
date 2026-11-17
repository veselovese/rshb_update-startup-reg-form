package ru.rshb.intech.digital.subsystem.events;

import jakarta.annotation.Nonnull;

public interface EventSource {

  @Nonnull
  String getTitle();

}
