package ru.rshb.intech.digital.notifications.api.channel;

import ru.rshb.intech.digital.notifications.api.message.Message;
import ru.rshb.intech.digital.notifications.api.sender.Sender;

import jakarta.annotation.Nonnull;

public interface Channel {

  @Nonnull
  String getId();

  @Nonnull
  ChannelType getType();

  @Nonnull
  Sender getSender();

  void append(@Nonnull Message message);
}
