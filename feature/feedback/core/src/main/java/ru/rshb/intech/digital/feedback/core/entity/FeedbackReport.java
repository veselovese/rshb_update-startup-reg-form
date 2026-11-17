package ru.rshb.intech.digital.feedback.core.entity;

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
    schema = "digital_feedback",
    name = "o_plt_feedback_reports"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class FeedbackReport {

  public static final int REPORTER_NAME_LENGTH = 300;
  public static final int EMAIL_LENGTH = 254;
  public static final int PHONE_LENGTH = 15;
  public static final int FEEDBACK_LENGTH = 1000;
  public static final int LOG_LENGTH = 1000;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "reporter_name",
      length = REPORTER_NAME_LENGTH
  )
  private String reporterName;

  @Column(
      name = "email",
      length = EMAIL_LENGTH,
      nullable = false
  )
  private String email;

  @Column(
      name = "phone",
      length = PHONE_LENGTH
  )
  private String phone;

  @Column(
      name = "feedback",
      length = FEEDBACK_LENGTH,
      nullable = false
  )
  private String feedback;

  @Column(
      name = "submit_date"
  )
  private LocalDateTime submitDate;

  @Column(
      name = "suspicious",
      nullable = false
  )
  private boolean suspicious;
}
