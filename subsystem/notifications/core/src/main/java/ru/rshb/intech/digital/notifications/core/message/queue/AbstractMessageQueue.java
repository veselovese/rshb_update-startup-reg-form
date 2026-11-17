package ru.rshb.intech.digital.notifications.core.message.queue;

import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageProcessor;
import ru.rshb.intech.digital.notifications.api.message.queue.MessageQueue;
import ru.rshb.intech.digital.notifications.api.message.scheduler.MessageQueueScheduler;

import jakarta.annotation.Nonnull;

@RequiredArgsConstructor
public abstract class AbstractMessageQueue implements MessageQueue {

  private final MessageProcessor processor;
  private final MessageQueueScheduler scheduler;


  @Nonnull
  protected MessageProcessor processor() {
    return processor;
  }

  @Nonnull
  protected MessageQueueScheduler scheduler() {
    return scheduler;
  }
}
