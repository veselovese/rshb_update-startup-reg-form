package ru.rshb.intech.digital.subsystem.audit.notifications.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.subsystem.audit.notifications.NotificationLogException;
import ru.rshb.intech.digital.subsystem.audit.notifications.entity.NotificationLogRecord;
import ru.rshb.intech.digital.subsystem.audit.notifications.repository.NotificationLogRepository;

@Service
@RequiredArgsConstructor
public class NotificationLogServiceImpl implements NotificationLogService {

  private final NotificationLogRepository logRepository;


  @Transactional
  @Override
  public void createNewEntry(@Nonnull NotificationLogRecord entry) {
    try {
      logRepository.save(entry);
    } catch (Exception e) {
      throw new NotificationLogException("Notification entry saving error", e);
    }
  }
}
