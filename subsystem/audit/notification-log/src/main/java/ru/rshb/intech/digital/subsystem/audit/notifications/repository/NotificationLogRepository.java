package ru.rshb.intech.digital.subsystem.audit.notifications.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.subsystem.audit.notifications.entity.NotificationLogRecord;

public interface NotificationLogRepository extends JpaRepository<NotificationLogRecord, Long> {

}
