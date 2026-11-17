package ru.rshb.intech.digital.notifications.core.message.processor;

import lombok.extern.slf4j.Slf4j;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageProcessor;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageProcessorContext;

import jakarta.annotation.Nonnull;

@Slf4j
public class DebugMessageProcessor implements MessageProcessor {

  @Override
  public void process(@Nonnull MessageProcessorContext context) {

    final var sender = context.getSender();
    final var message = context.getMessage();

    log.info(
        "SEND MESSAGE: \n\tFrom: {}\n\tTo: {}\n\tSubject: {}",
        sender.getId(),
        message.getRecipients(),
        message.getContent().getSubject()
    );
  }
}
