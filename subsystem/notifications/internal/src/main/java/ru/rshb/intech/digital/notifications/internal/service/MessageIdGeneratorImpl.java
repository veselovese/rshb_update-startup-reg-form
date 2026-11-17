package ru.rshb.intech.digital.notifications.internal.service;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageIdGeneratorImpl implements MessageIdGenerator {

  @Nonnull
  @Override
  public String nextMessageId() {
    return UUID.randomUUID().toString();
  }
}
