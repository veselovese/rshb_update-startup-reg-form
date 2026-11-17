package ru.rshb.intech.digital.notifications.mail.channel;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import ru.rshb.intech.digital.notifications.api.channel.ChannelType;
import ru.rshb.intech.digital.notifications.api.message.queue.MessageQueue;
import ru.rshb.intech.digital.notifications.api.sender.Sender;
import ru.rshb.intech.digital.notifications.core.channel.BasicChannel;

@Slf4j
public class BasicEmailChannel extends BasicChannel {

  public BasicEmailChannel(@Nonnull String id, @Nonnull Sender sender, @Nonnull MessageQueue messageQueue) {
    super(id, ChannelType.EMAIL, sender, messageQueue);
  }
}
