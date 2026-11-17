package ru.rshb.intech.digital.subsystem.contests.stipend.entity;

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
    name = "stipend_task_solution"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class StipendContestSolution {

  public static final int PARTICIPANT_NAME_LENGTH = 255;
  public static final int COMMENT_LENGTH = 1000;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "submit_date",
      nullable = false
  )
  private LocalDateTime submitDate;

  @Column(
      name = "participant_name",
      nullable = false
  )
  private String participantName;

  @Column(
      name = "task_id",
      nullable = false
  )
  private String taskId;

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
      name = "comment",
      nullable = false
  )
  private String comment;

  @Column(
      name = "suspicious",
      nullable = false
  )
  private Boolean suspicious = false;
}
