package ru.rshb.intech.digital.subsystem.audit;

import ru.rshb.intech.digital.subsystem.audit.entity.AuditLogEntry;
import ru.rshb.intech.digital.subsystem.audit.event.AuditLogEvent;

import jakarta.annotation.Nonnull;
import java.util.Optional;

public interface AuditLog {

  @Nonnull
  Optional<AuditLogEntry> log(@Nonnull AuditLogEvent logEntry);
}
