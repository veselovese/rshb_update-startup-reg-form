package ru.rshb.intech.digital.subsystem.audit.notifications.service;

import ru.rshb.intech.digital.subsystem.audit.notifications.entity.NotificationLogRecord;

import jakarta.annotation.Nonnull;

public interface NotificationLogService {

  void createNewEntry(@Nonnull NotificationLogRecord entry);
}
