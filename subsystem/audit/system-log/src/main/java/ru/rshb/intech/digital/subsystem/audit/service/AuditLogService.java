package ru.rshb.intech.digital.subsystem.audit.service;

import ru.rshb.intech.digital.subsystem.audit.entity.AuditLogEntry;

import jakarta.annotation.Nonnull;
import java.util.List;

public interface AuditLogService {

  @Nonnull
  List<AuditLogEntry> findAll();
}
