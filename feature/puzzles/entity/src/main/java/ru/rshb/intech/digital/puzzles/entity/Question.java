package ru.rshb.intech.digital.puzzles.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rshb.intech.digital.content.model.Crossword.Question.Direction;

@Entity
@Table(
    schema = "digital_puzzles",
    name = "o_plt_cwd_questions"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "externalId"})
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "external_id",
      updatable = false
  )
  private String externalId;

  private String title;

  @ManyToOne(
      cascade = {
          CascadeType.MERGE,
          CascadeType.PERSIST
      },
      fetch = FetchType.LAZY
  )
  private Crossword crossword;

  @Column(name = "pos_row")
  private Long row;

  @Column(name = "pos_column")
  private Long column;

  private String answer;

  @Enumerated(EnumType.STRING)
  private Direction direction;
}
