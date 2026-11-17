package ru.rshb.intech.digital.puzzles.core.crossword.service.question;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.puzzles.dto.Answer;
import ru.rshb.intech.digital.puzzles.dto.CrosswordAnswers;
import ru.rshb.intech.digital.puzzles.dto.CrosswordCheckResult;
import ru.rshb.intech.digital.puzzles.dto.GuessResult;

public interface CrosswordQuestionService {

  @Nonnull
  Answer answer(@Nonnull String qId);

  @Nonnull
  GuessResult isRightAnswer(@Nonnull String qId, @Nonnull String guess);

  @Nonnull
  CrosswordCheckResult checkAnswers(@Nonnull String crosswordId, @Nonnull CrosswordAnswers answers);
}
