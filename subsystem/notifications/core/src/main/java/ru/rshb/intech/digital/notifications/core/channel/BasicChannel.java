package ru.rshb.intech.digital.notifications.core.channel;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.rshb.intech.digital.notifications.api.channel.Channel;
import ru.rshb.intech.digital.notifications.api.channel.ChannelType;
import ru.rshb.intech.digital.notifications.api.message.Message;
import ru.rshb.intech.digital.notifications.api.message.queue.MessageQueue;
import ru.rshb.intech.digital.notifications.api.message.queue.SendTask;
import ru.rshb.intech.digital.notifications.api.sender.Sender;

import jakarta.annotation.Nonnull;

@Slf4j
@Getter
@Builder
@RequiredArgsConstructor
public class BasicChannel implements Channel {

  private final String id;
  private final ChannelType type;
  private final Sender sender;
  private final MessageQueue messageQueue;


  @Override
  public void append(@Nonnull Message message) {

    if (message.getRecipients().isEmpty()) {
      log.warn("Message could not be added to queue because no recipients provided");
      return;
    }

    messageQueue.append(
        SendTask.builder()
            .sender(sender)
            .message(message)
            .build()
    );
  }
}
