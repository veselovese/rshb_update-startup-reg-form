package ru.rshb.intech.digital.subscriptions.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(
    schema = "digital_subscriptions",
    name = "o_plt_subscriptions"
)
@Builder
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Subscription {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "email",
      nullable = false,
      unique = true,
      length = 254
  )
  private String email;

  @Column(
      name = "creation_date",
      nullable = false
  )
  private LocalDateTime creationDate;

  @Column(name = "activation_date")
  private LocalDateTime activationDate;

  @Column(name = "deactivation_date")
  private LocalDateTime deactivationDate;

  @Column(name = "blocking_date")
  private LocalDateTime blockingDate;

  @Column(name = "renewal_date")
  private LocalDateTime renewalDate;

  @Column(name = "activation_code")
  private String activationCode;

  @Column(name = "deactivation_code")
  private String deactivationCode;

  @ManyToMany(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      fetch = FetchType.EAGER
  )
  @JoinTable(
      schema = "digital_subscriptions",
      name = "o_plt_sub_to_cat_active",
      joinColumns = @JoinColumn(name = "sub_id"),
      inverseJoinColumns = @JoinColumn(name = "cat_id")
  )
  private Set<SubscriptionCategory> activeCategories;

  @ManyToMany(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      fetch = FetchType.EAGER
  )
  @JoinTable(
      schema = "digital_subscriptions",
      name = "o_plt_sub_to_cat_pending",
      joinColumns = @JoinColumn(name = "sub_id"),
      inverseJoinColumns = @JoinColumn(name = "cat_id")
  )
  private Set<SubscriptionCategory> pendingCategories;


  public boolean isActivated() {
    return (activationDate != null);
  }

  public boolean isBlocked() {
    return (blockingDate != null);
  }
}
