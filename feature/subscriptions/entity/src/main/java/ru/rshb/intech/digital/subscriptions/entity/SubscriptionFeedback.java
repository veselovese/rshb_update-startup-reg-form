package ru.rshb.intech.digital.subscriptions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    schema = "digital_subscriptions",
    name = "o_plt_feedback_reports"
)
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
public class SubscriptionFeedback {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "email",
      length = 254
  )
  private String email;

  @Column(
      name = "reasons",
      length = 1000
  )
  private String reasons;

  @Column(
      name = "feedback",
      length = 1000
  )
  private String feedback;

  @Column(
      name = "submit_date",
      nullable = false
  )
  private LocalDateTime submitDate;
}
