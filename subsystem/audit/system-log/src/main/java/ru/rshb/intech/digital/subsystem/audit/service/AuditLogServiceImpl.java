package ru.rshb.intech.digital.subsystem.audit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.subsystem.audit.entity.AuditLogEntry;
import ru.rshb.intech.digital.subsystem.audit.repository.AuditLogRepository;

import jakarta.annotation.Nonnull;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

  private final AuditLogRepository auditLogRepository;

  @Nonnull
  @Override
  public List<AuditLogEntry> findAll() {
    return auditLogRepository.findAllByDeletedFalse();
  }
}
