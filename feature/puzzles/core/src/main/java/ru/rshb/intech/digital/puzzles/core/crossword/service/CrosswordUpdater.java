package ru.rshb.intech.digital.puzzles.core.crossword.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.content.model.Crossword;

import java.util.List;

public interface CrosswordUpdater {

  void update(@Nonnull List<Crossword> cachedCrosswords);
}
