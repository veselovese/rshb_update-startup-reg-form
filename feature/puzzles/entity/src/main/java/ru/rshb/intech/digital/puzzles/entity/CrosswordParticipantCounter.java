package ru.rshb.intech.digital.puzzles.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    schema = "digital_puzzles",
    name = "o_plt_participant_counters"
)
@Getter
@Setter
@Builder
@Generated
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CrosswordParticipantCounter {

  @Id
  @Column(
      name = "crossword_external_id",
      nullable = false,
      unique = true
  )
  private String crosswordExternalId;

  @Column(
      name = "interaction_count",
      nullable = false
  )
  private Long interactionCount;
}
