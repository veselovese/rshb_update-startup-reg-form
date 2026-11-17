package ru.rshb.intech.digital.subsystem.audit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rshb.intech.digital.subsystem.audit.event.LogLevel;
import ru.rshb.intech.digital.subsystem.events.SubSystem;

import java.time.LocalDateTime;

@Entity
@Table(
    schema = "digital_system",
    name = "audit_log"
)
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
public class AuditLogEntry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "event_ts",
      nullable = false
  )
  private LocalDateTime eventTime;

  @Column(
      name = "level",
      nullable = false
  )
  @Enumerated(EnumType.STRING)
  private LogLevel logLevel;

  private String instance;

  @Column(
      name = "subsystem",
      nullable = false
  )
  @Enumerated(EnumType.STRING)
  private SubSystem subSystem;

  @Column(
      name = "initiator",
      nullable = false
  )
  private String initiator;

  @Column(
      name = "title",
      nullable = false
  )
  private String title;

  @Column(
      name = "description"
  )
  private String description;

  @Column(
      name = "deleted",
      nullable = false
  )
  private Boolean deleted;

  @Column(
      name = "viewed",
      nullable = false
  )
  private Boolean viewed;
}
