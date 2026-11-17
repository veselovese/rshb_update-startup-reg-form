package ru.rshb.intech.digital.subsystem.audit.notifications.entity;

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

import java.time.LocalDateTime;

@Entity
@Table(
    schema = "digital_notifications",
    name = "notification_log"
)
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
public class NotificationLogRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "event_ts",
      nullable = false
  )
  private LocalDateTime eventTime;

  @Column(
      name = "status",
      nullable = false
  )
  @Enumerated(EnumType.STRING)
  private NotificationSendingStatus sendingStatus;

  @Column(
      name = "channels",
      nullable = false
  )
  private String channels;

  @Column(
      name = "msg_id"
  )
  private String messageId;

  @Column(
      name = "recipients",
      nullable = false
  )
  private String recipients;

  @Column(
      name = "collated",
      nullable = false
  )
  private Boolean collated = false;

  @Column(
      name = "title",
      nullable = false
  )
  private String title;

  @Column(
      name = "description",
      nullable = true
  )
  private String description;

  @Column(
      name = "deleted",
      nullable = false
  )
  private Boolean deleted;
}
