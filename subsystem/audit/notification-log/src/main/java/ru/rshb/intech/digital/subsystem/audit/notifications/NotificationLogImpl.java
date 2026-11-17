package ru.rshb.intech.digital.subsystem.audit.notifications;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.subsystem.audit.notifications.entity.NotificationLogRecord;
import ru.rshb.intech.digital.subsystem.audit.notifications.event.NotificationLogEvent;
import ru.rshb.intech.digital.subsystem.audit.notifications.service.NotificationLogService;

import jakarta.annotation.Nonnull;
import java.time.LocalDateTime;

@Slf4j
@Transactional
@Service
public class NotificationLogImpl implements NotificationLog {

  private final NotificationLogService logService;


  public NotificationLogImpl(@Nonnull NotificationLogService logService) {
    this.logService = logService;
  }

  private void write(@Nonnull NotificationLogRecord logRecord) {
    try {
      logService.createNewEntry(logRecord);
    } catch (Exception e) {
      log.error("Notification log entry save error", e);
    }
  }

  @EventListener(NotificationLogEvent.class)
  public void handleNotificationLogEvent(@Nonnull NotificationLogEvent event) {

    final var logRecord = new NotificationLogRecord();
    logRecord.setEventTime(LocalDateTime.now());
    logRecord.setSendingStatus(event.getStatus());
    logRecord.setChannels(event.getChannels());
    logRecord.setMessageId(event.getMessageId());
    logRecord.setRecipients(event.getRecipients());
    logRecord.setCollated(event.isCollated());
    logRecord.setTitle(event.getTitle());
    logRecord.setDescription(event.getDescription());
    logRecord.setDeleted(false);

    write(logRecord);
  }
}
