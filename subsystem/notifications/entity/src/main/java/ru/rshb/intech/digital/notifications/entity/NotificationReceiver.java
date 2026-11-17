package ru.rshb.intech.digital.notifications.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Generated
@MappedSuperclass
@EqualsAndHashCode(of = {"id", "email"})
public class NotificationReceiver {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "name",
      nullable = false,
      length = 300
  )
  private String name;

  @Column(
      name = "email",
      nullable = false,
      unique = true,
      length = 320
  )
  private String email;

  @Column(
      name = "enabled"
  )
  private Boolean enabled;

  @Column(
      name = "deleted"
  )
  private Boolean deleted;
}
