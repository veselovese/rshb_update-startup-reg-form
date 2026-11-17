package ru.rshb.intech.digital.notifications.api.message;

import ru.rshb.intech.digital.notifications.api.message.processor.MessageCallback;

import jakarta.annotation.Nonnull;
import java.util.Optional;
import java.util.Set;

public interface Message extends Cloneable {

  @Nonnull
  String getId();

  @Nonnull
  Set<String> getChannels();

  @Nonnull
  MessageContent getContent();

  @Nonnull
  Set<String> getRecipients();

  void setRecipients(@Nonnull Set<String> receivers);

  boolean isCollate();

  @Nonnull
  Optional<MessageCallback> getBeforeProcess();

  @Nonnull
  Optional<MessageCallback> getAfterProcess();

  @Nonnull
  Message clone();
}
