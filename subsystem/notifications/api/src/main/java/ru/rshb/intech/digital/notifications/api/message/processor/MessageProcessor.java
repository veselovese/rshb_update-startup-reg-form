package ru.rshb.intech.digital.notifications.api.message.processor;

import jakarta.annotation.Nonnull;

public interface MessageProcessor {

  void process(@Nonnull MessageProcessorContext context);
}
