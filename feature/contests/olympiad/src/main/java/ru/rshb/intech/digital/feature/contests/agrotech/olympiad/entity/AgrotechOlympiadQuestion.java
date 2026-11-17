package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity;

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
    schema = "digital_contests",
    name = "agrotech_olympiad_question"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class AgrotechOlympiadQuestion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "submit_date",
      nullable = false
  )
  private LocalDateTime submitDate;

  @Column(
      name = "applicant_name",
      nullable = false
  )
  private String applicantName;

  @Column(
      name = "email",
      nullable = false
  )
  private String email;

  @Column(
      name = "phone",
      nullable = false
  )
  private String phone;

  @Column(
      name = "question",
      nullable = false
  )
  private String question;

  @Column(
      name = "suspicious",
      nullable = false
  )
  private Boolean suspicious = false;
}
