package ru.rshb.intech.digital.subscriptions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    schema = "digital_subscriptions",
    name = "o_plt_banned_emails"
)
@Getter
@Setter
@Builder
@Generated
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class BannedEmail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "email",
      unique = true,
      length = 254,
      nullable = false
  )
  private String email;

  @Column(
      name = "deleted",
      nullable = false
  )
  private Boolean deleted;
}
