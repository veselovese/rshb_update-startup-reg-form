package ru.rshb.intech.digital.feature.internship.core.entity;

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
    schema = "digital_internship",
    name = "feedback"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class InternshipFeedback {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "submit_dt",
      nullable = false
  )
  private LocalDateTime submitDate;

  @Column(
      name = "trainee_name",
      nullable = false
  )
  private String traineeName;

  @Column(nullable = false)
  private String phone;

  @Column(nullable = false)
  private String email;

  @Column(
      name = "tasks_grade",
      nullable = false
  )
  private Integer tasksGrade;

  @Column(
      name = "skills",
      nullable = false
  )
  private String skills;

  private String mentor;

  @Column(
      name = "mentor_grade",
      nullable = false
  )
  private Integer mentorGrade;

  @Column(
      name = "team_division",
      nullable = false
  )
  private String teamDivision;

  @Column(
      name = "team_comm_grade",
      nullable = false
  )
  private Integer teamCommunicationGrade;

  @Column(
      name = "expect_lvl_grade",
      nullable = false
  )
  private Integer expectationLevelGrade;

  private String comment;

  @Column(
      name = "recommend_src",
      nullable = false
  )
  private String recommendationSource;

  @Column(name = "recommend_cause")
  private String recommendation;

  private String review;

  private Boolean suspicious = false;
}
