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

@Entity
@Table(
    schema = "digital_contests",
    name = "stipend_task"
)
@Getter
@Setter
@Generated
@EqualsAndHashCode(of = {"id", "taskId"})
@NoArgsConstructor
public class StipendTask {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "task_id",
      nullable = false
  )
  private String taskId;

  @Column(
      name = "task_title",
      nullable = false
  )
  private String taskName;

  @Column(
      name = "enabled",
      nullable = false
  )
  private Boolean enabled = true;
}
