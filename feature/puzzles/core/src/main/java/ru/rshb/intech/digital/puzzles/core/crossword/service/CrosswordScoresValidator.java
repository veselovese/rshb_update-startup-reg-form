package ru.rshb.intech.digital.puzzles.core.crossword.service;


import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.puzzles.entity.CrosswordScores;

public interface CrosswordScoresValidator {

  @Nonnull
  CrosswordScores validate(@Nonnull CrosswordScores crosswordScores);
}
