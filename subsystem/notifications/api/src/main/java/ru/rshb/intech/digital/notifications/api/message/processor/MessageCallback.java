package ru.rshb.intech.digital.notifications.api.message.processor;

import jakarta.annotation.Nonnull;

public interface MessageCallback {

  void proceed(@Nonnull MessageProcessorContext context);
}
