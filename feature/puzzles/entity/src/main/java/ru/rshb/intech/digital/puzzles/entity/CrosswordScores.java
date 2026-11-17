package ru.rshb.intech.digital.puzzles.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
    schema = "digital_puzzles",
    name = "o_plt_crossword_scores"
)
@Getter
@Setter
@Builder
@Generated
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CrosswordScores {

  public static final int NAME_MAX_LENGTH = 300;

  @EmbeddedId
  private ScoreId scoreId;

  @MapsId("sessionId")
  @Column(
      name = "session_id",
      nullable = false,
      insertable = false,
      updatable = false
  )
  private String sessionId;

  @Column(
      name = "participant_name",
      nullable = false,
      length = NAME_MAX_LENGTH
  )
  private String participantName;

  @Column(
      name = "submit_date",
      nullable = false
  )
  private LocalDateTime submitDate;

  @MapsId("crosswordId")
  @ManyToOne(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      fetch = FetchType.LAZY
  )
  private Crossword crossword;

  @Column(
      name = "result",
      nullable = false
  )
  private Long result;

  @Override
  public String toString() {
    return String.format(
        "session_id=%s\n" +
        "participant_name=%s\n" +
        "submit_date=%s\n" +
        "result=%s\n" +
        "crossword_id=%s\n",
        scoreId.getSessionId(),
        participantName,
        submitDate,
        result,
        scoreId.getCrosswordId()
    );
  }
}
