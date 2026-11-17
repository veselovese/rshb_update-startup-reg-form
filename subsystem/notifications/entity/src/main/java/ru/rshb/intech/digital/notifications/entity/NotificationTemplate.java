package ru.rshb.intech.digital.notifications.entity;

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

@Entity
@Table(
    schema = "digital_notifications",
    name = "templates"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "key"})
public class NotificationTemplate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "key",
      nullable = false
  )
  private String key;

  @Column(
      name = "type",
      nullable = false
  )
  @Enumerated(EnumType.STRING)
  private TemplateType type;

  @Column(name = "default_subject")
  private String defaultSubject;

  @Column(
      name = "content",
      nullable = false
  )
  private String content;
}
