package ru.rshb.intech.digital.puzzles.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    schema = "digital_puzzles",
    name = "o_plt_crosswords"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "externalId"})
public class Crossword {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "external_id",
      unique = true,
      nullable = false,
      updatable = false
  )
  private String externalId;

  @Column(
      name = "title",
      length = 300
  )
  private String title;

  @Column(
      name = "description",
      length = 1000
  )
  private String description;

  @ManyToMany(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      fetch = FetchType.LAZY,
      mappedBy = "crosswords"
  )
  private Set<CrosswordCategory> categories = new HashSet<>();

  @OneToMany(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      fetch = FetchType.LAZY,
      mappedBy = "crossword"
  )
  private Set<Question> questions = new HashSet<>();

  private Boolean deleted = false;


  public void addCategory(@Nonnull CrosswordCategory crosswordCategory) {
    this.categories.add(crosswordCategory);
    crosswordCategory.addCrossword(this);
  }

  public void addCategories(@Nonnull Collection<CrosswordCategory> categories) {
    categories.forEach(this::addCategory);
  }

  public void addQuestion(@Nonnull Question question) {
    this.questions.add(question);
    question.setCrossword(this);
  }

  public void addQuestions(@Nonnull Collection<Question> questions) {
    questions.forEach(this::addQuestion);
  }
}
