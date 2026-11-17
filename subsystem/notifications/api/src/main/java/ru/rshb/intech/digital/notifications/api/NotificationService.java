package ru.rshb.intech.digital.notifications.api;

import ru.rshb.intech.digital.notifications.api.message.Message;

import jakarta.annotation.Nonnull;

public interface NotificationService {

  void send(@Nonnull Message notificationMessage);
}
