package ru.rshb.intech.digital.notifications.api.message.queue;

import jakarta.annotation.Nonnull;

public interface MessageQueue {

  void append(@Nonnull SendTask sendTask);
}
