package ru.rshb.intech.digital.notifications.internal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.notifications.api.Notification;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;
import ru.rshb.intech.digital.notifications.api.NotificationService;
import ru.rshb.intech.digital.notifications.api.message.BasicMessage;
import ru.rshb.intech.digital.notifications.api.message.Message;
import ru.rshb.intech.digital.notifications.api.message.MessageContent;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageCallback;
import ru.rshb.intech.digital.notifications.entity.NotificationReceiver;
import ru.rshb.intech.digital.notifications.entity.NotificationTopic;
import ru.rshb.intech.digital.notifications.mail.channel.EmailChannels;

import jakarta.annotation.Nonnull;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InternalNotificationServiceImpl implements InternalNotificationService {

  private final NotificationService notificationService;
  private final InternalNotificationSender internalNotificationSender;


  @Override
  public void notifyTopic(@Nonnull NotificationTopic topic,
                          @Nonnull MessageContent messageContent) {

    final var recipients = topic.getRecipients()
        .stream()
        .map(NotificationReceiver::getEmail)
        .collect(Collectors.toSet());

    final var message = BasicMessage.builder()
        .recipients(recipients)
        .content(messageContent)
        .channels(Set.of(EmailChannels.DEFAULT))
        .collate(false)
        .build();

    notificationService.send(message);
  }

  @Override
  public void notifyTopic(@Nonnull NotificationTopic topic, @Nonnull Message message) {

    final var recipients = topic.getRecipients()
        .stream()
        .map(NotificationReceiver::getEmail)
        .collect(Collectors.toSet());

    notificationService.send(
        BasicMessage.builder()
            .recipients(recipients)
            .channels(Set.of(EmailChannels.DEFAULT))
            .content(message.getContent())
            .beforeProcess(message.getBeforeProcess().orElse(null))
            .afterProcess(message.getAfterProcess().orElse(null))
            .collate(false)
            .build()
    );
  }

  @Override
  public void send(@Nonnull NotificationMessage message) {
    send(new Notification(message));
  }

  @Override
  public void send(@Nonnull NotificationMessage message, MessageCallback before, MessageCallback after) {
    send(
        Notification.builder()
            .message(message)
            .beforeSend(before)
            .afterSend(after)
            .build()
    );
  }

  @Async
  @Override
  public void send(@Nonnull Notification notification) {
    try {
      internalNotificationSender.send(notification);
    } catch (Exception e) {
      log.error("Internal notification sending error", e);
    }
  }
}
