package ru.rshb.intech.digital.notifications.internal.service;

import jakarta.annotation.Nonnull;

public interface MessageIdGenerator {

  @Nonnull
  String nextMessageId();

}
