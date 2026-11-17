package ru.rshb.intech.digital.notifications.internal.service;

import ru.rshb.intech.digital.notifications.entity.NotificationTopic;

import jakarta.annotation.Nonnull;

public interface InternalNotificationTopicService {

  @Nonnull
  NotificationTopic getTopicByKey(@Nonnull String topicKey);
}
