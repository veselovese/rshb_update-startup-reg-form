package ru.rshb.intech.digital.puzzles.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    schema = "digital_puzzles",
    name = "o_plt_crossword_categories"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "externalId", "title"})
public class CrosswordCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @Column(
      name = "external_id",
      nullable = false,
      unique = true,
      updatable = false
  )
  private String externalId;


  @Column(
      name = "title",
      nullable = false
  )
  private String title;

  @ManyToMany(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      fetch = FetchType.LAZY
  )
  @JoinTable(
      schema = "digital_puzzles",
      name = "o_plt_crossword_to_category",
      joinColumns = @JoinColumn(name = "cat_id"),
      inverseJoinColumns = @JoinColumn(name = "cwd_id")
  )
  private Set<Crossword> crosswords = new HashSet<>();


  public CrosswordCategory(@Nonnull String externalId, @Nonnull String title) {
    super();
    setExternalId(externalId);
    setTitle(title);
  }


  public void addCrossword(@Nonnull Crossword crossword) {
    this.crosswords.add(crossword);
  }
}
