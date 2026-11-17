package ru.rshb.intech.digital.puzzles.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"session_id", "crossword_id"})
public class ScoreId implements Serializable {

  private String session_id;
  private Long crossword_id;

  public String getSessionId() {
    return session_id;
  }

  public void setSessionId(String session_id) {
    this.session_id = session_id;
  }

  public Long getCrosswordId() {
    return crossword_id;
  }

  public void setCrosswordId(Long crossword_id) {
    this.crossword_id = crossword_id;
  }
}
