package ru.rshb.intech.digital.notifications.internal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.notifications.entity.NotificationTopic;
import ru.rshb.intech.digital.notifications.internal.exception.InternalNotificationException;
import ru.rshb.intech.digital.notifications.internal.repository.InternalNotificationTopicRepository;

import jakarta.annotation.Nonnull;

@Service
@RequiredArgsConstructor
public class InternalNotificationTopicServiceImpl implements InternalNotificationTopicService {

  private final InternalNotificationTopicRepository topicRepository;

  @Nonnull
  @Override
  public NotificationTopic getTopicByKey(@Nonnull String topicKey) {
    return topicRepository.findAvailableByKey(topicKey)
        .orElseThrow(() -> new InternalNotificationException("Notification topic not found: " + topicKey));
  }
}
