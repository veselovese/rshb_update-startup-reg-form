package ru.rshb.intech.digital.puzzles.core.crossword.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static org.apache.commons.lang3.BooleanUtils.toBooleanDefaultIfNull;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrosswordSearchOptions {

  private String categoryId;
  private String sessionId;

  @Getter(AccessLevel.PRIVATE)
  private Boolean solved;

  @Getter(AccessLevel.PRIVATE)
  private Boolean withQuestions;


  public boolean isShowSolved() {
    return (solved != null && solved);
  }

  public boolean isShowNotSolved() {
    return (solved != null && !solved);
  }

  public boolean isShowAll() {
    return (solved == null);
  }

  public boolean isWithQuestions() {
    return toBooleanDefaultIfNull(withQuestions, false);
  }
}
