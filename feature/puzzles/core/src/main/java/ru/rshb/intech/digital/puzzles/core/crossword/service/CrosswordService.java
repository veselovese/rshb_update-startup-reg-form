package ru.rshb.intech.digital.puzzles.core.crossword.service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import ru.rshb.intech.digital.puzzles.entity.Crossword;
import ru.rshb.intech.digital.puzzles.entity.CrosswordCategory;
import ru.rshb.intech.digital.puzzles.entity.CrosswordGuessResult;

import java.util.List;
import java.util.Map;

public interface CrosswordService {

  @Nonnull
  Crossword getCrosswordById(@Nonnull String id);

  @Nonnull
  List<Crossword> findByCategoryId(@Nullable String categoryId);

  @Nonnull
  List<Crossword> findBy(@Nonnull CrosswordSearchOptions searchOptions);

  @Nonnull
  String getAnswer(@Nonnull String questionExternalId);

  boolean isGuessRight(@Nonnull String questionId, @Nonnull String guess);

  @Nonnull
  CrosswordGuessResult getWrongAnswers(@Nonnull String crosswordId,
                                       @Nonnull Map<String, String> providedAnswers);

  @Nonnull
  List<CrosswordCategory> getAllCategories();

  @Nonnull
  List<Crossword> findAll();
}
