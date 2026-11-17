package ru.rshb.intech.digital.notifications.internal.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.notifications.api.Notification;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;
import ru.rshb.intech.digital.notifications.api.message.Message;
import ru.rshb.intech.digital.notifications.api.message.MessageContent;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageCallback;
import ru.rshb.intech.digital.notifications.entity.NotificationTopic;

public interface InternalNotificationService {

  void notifyTopic(@Nonnull NotificationTopic topic, @Nonnull MessageContent messageContent);

  void notifyTopic(@Nonnull NotificationTopic topic, @Nonnull Message message);

  void send(@Nonnull NotificationMessage notification);

  void send(@Nonnull NotificationMessage notification, MessageCallback before, MessageCallback after);

  void send(@Nonnull Notification notification);
}
