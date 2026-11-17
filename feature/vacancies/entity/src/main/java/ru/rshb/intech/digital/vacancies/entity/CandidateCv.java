package ru.rshb.intech.digital.vacancies.entity;

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
    schema = "digital_vacancies",
    name = "candidate_cv"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class CandidateCv {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "submit_dt",
      nullable = false
  )
  private LocalDateTime submitDate;

  @Column(
      name = "candidate_name",
      nullable = false
  )
  private String candidateName;

  @Column(nullable = false)
  private String phone;

  @Column(nullable = false)
  private String email;

  @Column(
      name = "cov_letter",
      nullable = false,
      length = 1000
  )
  private String coveringLetter;

  @Column(nullable = false)
  Boolean suspicious = false;
}
