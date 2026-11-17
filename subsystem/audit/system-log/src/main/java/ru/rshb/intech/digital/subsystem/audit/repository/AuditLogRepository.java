package ru.rshb.intech.digital.subsystem.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.subsystem.audit.entity.AuditLogEntry;

import jakarta.annotation.Nonnull;
import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLogEntry, Long> {

  @Nonnull
  List<AuditLogEntry> findAllByDeletedFalse();

}
