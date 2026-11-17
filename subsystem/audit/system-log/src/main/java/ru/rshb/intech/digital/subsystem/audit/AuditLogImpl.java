package ru.rshb.intech.digital.subsystem.audit;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.subsystem.audit.entity.AuditLogEntry;
import ru.rshb.intech.digital.subsystem.audit.entity.EventInitiator;
import ru.rshb.intech.digital.subsystem.audit.event.AuditLogEvent;
import ru.rshb.intech.digital.subsystem.audit.repository.AuditLogRepository;
import ru.rshb.intech.digital.subsystem.audit.service.InstanceInfoProvider;
import ru.rshb.intech.digital.subsystem.events.SubSystem;

import jakarta.annotation.Nonnull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuditLogImpl implements AuditLog {

  private final AuditLogRepository auditLogRepository;
  private final InstanceInfoProvider instanceInfoProvider;

  @Nonnull
  @Override
  public Optional<AuditLogEntry> log(@Nonnull AuditLogEvent logEntry) {
    return log(LocalDateTime.now(), logEntry);
  }

  @Nonnull
  public Optional<AuditLogEntry> log(@Nonnull LocalDateTime dateTime, @Nonnull AuditLogEvent logEntry) {

    final var entry = new AuditLogEntry();
    entry.setEventTime(dateTime);
    entry.setLogLevel(logEntry.getLevel());
    entry.setInstance(instanceInfoProvider.getExternalUrl());
    entry.setSubSystem((SubSystem) logEntry.getSource());
    entry.setInitiator(EventInitiator.SYSTEM.getTitle()); // TODO выводить пользователя при наличии аутентификации
    entry.setTitle(logEntry.getTitle());
    entry.setDescription(logEntry.getDescription());
    entry.setDeleted(false);
    entry.setViewed(false);

    try {
      return Optional.of(auditLogRepository.save(entry));
    } catch (Exception e) {
      log.error("Audit log entry writing error", e);
      return Optional.empty();
    }
  }

  @EventListener(AuditLogEvent.class)
  public void handleAuditLogEvent(@Nonnull AuditLogEvent event) {

    log.debug("Handling audit log event from [{}]", event.getSource());

    final var logTimestamp = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(event.getTimestamp()),
        ZoneId.systemDefault()
    );

    log(logTimestamp, event);
  }
}
