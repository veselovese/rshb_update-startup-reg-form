package ru.rshb.intech.digital.notifications.api.message.queue;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.notifications.api.message.Message;
import ru.rshb.intech.digital.notifications.api.sender.Sender;

import jakarta.annotation.Nonnull;
import java.util.Set;

@Builder
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class SendTask {

  private final Sender sender;
  private final Message message;

  public SendTask(@Nonnull SendTask otherTask) {
    this(
        new Sender(otherTask.getSender()),
        otherTask.getMessage().clone()
    );
  }

  public SendTask(@Nonnull SendTask otherTask, @Nonnull Set<String> recipients) {
    this(otherTask);
    this.getMessage()
        .setRecipients(recipients);
  }
}
