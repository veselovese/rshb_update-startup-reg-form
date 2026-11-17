package ru.rshb.intech.digital.notifications.core.message.queue;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.rshb.intech.digital.notifications.api.exception.MessageProcessingException;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageCallback;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageProcessor;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageProcessorContext;
import ru.rshb.intech.digital.notifications.api.message.queue.SendTask;
import ru.rshb.intech.digital.notifications.api.message.scheduler.MessageQueueScheduler;
import ru.rshb.intech.digital.subsystem.audit.event.AuditLogEvent;
import ru.rshb.intech.digital.subsystem.audit.event.LogLevel;
import ru.rshb.intech.digital.subsystem.events.AppEventBus;
import ru.rshb.intech.digital.subsystem.events.SubSystem;

import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

import static ru.rshb.intech.digital.subsystem.audit.notifications.event.NotificationLogEvent.fail;
import static ru.rshb.intech.digital.subsystem.audit.notifications.event.NotificationLogEvent.success;

@Slf4j
public class InMemoryMessageQueue extends AbstractMessageQueue {

  private static final int BLOCK_SIZE = 100;
  private static final int QUEUE_BOUND = 1_000;

  @Getter(AccessLevel.PROTECTED)
  private final BlockingQueue<SendTask> queue;
  private final AppEventBus appEventBus;


  public InMemoryMessageQueue(@Nonnull MessageProcessor processor,
                              @Nonnull MessageQueueScheduler scheduler,
                              @Nonnull AppEventBus appEventBus) {

    super(processor, scheduler);

    this.queue = new LinkedBlockingQueue<>(QUEUE_BOUND);
    this.appEventBus = appEventBus;
    this.scheduler()
        .submit(() -> processNext(BLOCK_SIZE));
  }


  @Override
  public void append(@Nonnull SendTask sendTask) {
    try {
      if (!sendTask.getMessage().isCollate()) {
        queue.put(sendTask);
        return;
      }

      for (final var recipient : sendTask.getMessage().getRecipients()) {
        final var forkedTask = new SendTask(sendTask, Set.of(recipient));
        queue.put(forkedTask);
      }
    } catch (Exception e) {
      log.error("Message queue put error", e);
    }
  }

  protected void processNext(int itemsNumber) {

    if (queue.isEmpty()) {
      return;
    }

    log.debug("Process next: {}", itemsNumber);

    int processed = 0;
    for (int i = 0; i < itemsNumber; i++) {
      try {
        if (!queue.iterator().hasNext()) {
          break;
        }

        final var message = queue.poll();
        if (message == null) {
          break;
        }

        process(message);
        processed++;
      } catch (Exception e) {
        log.error("Message queue item processing error", e);
        appEventBus.log(
            new AuditLogEvent(this, LogLevel.ERROR, SubSystem.NOTIFICATIONS)
                .withTitleLocalized("audit.event.notifications.processing.error.title")
                .withDescriptionLocalized("audit.event.notifications.processing.error.description")
                .withThrowable(e)
        );
      }
    }

    if (processed > 0) {
      log.info("Messages processed: {}. Left: {}", processed, queue.size());
    }
  }

  @Nonnull
  private MessageProcessorContext process(@Nonnull SendTask sendTask) {

    final var sender = sendTask.getSender();
    final var message = sendTask.getMessage();

    final var context = MessageProcessorContext.builder()
        .sender(sender)
        .message(message)
        .build();

    message.getBeforeProcess()
        .ifPresent(new CallbackConsumer(context));

    try {
      processor().process(context);
      appEventBus.log(success(this, message));
    } catch (Exception e) {
      context.setException(e);
      log.error("Message processing error", e);
      appEventBus.log(fail(this, message, e));
    }

    message.getAfterProcess()
        .ifPresent(new CallbackConsumer(context));

    return context;
  }

  @RequiredArgsConstructor
  private static class CallbackConsumer implements Consumer<MessageCallback> {

    private final MessageProcessorContext context;

    @Override
    public void accept(@Nonnull MessageCallback messageCallback) {
      try {
        messageCallback.proceed(context);
      } catch (Exception e) {
        throw new MessageProcessingException("Notification message processor callback error", e);
      }
    }
  }
}
