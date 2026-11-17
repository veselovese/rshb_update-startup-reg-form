package ru.rshb.intech.digital.puzzles.core.crossword.exception;

import ru.rshb.intech.digital.messages.I18n;

/**
 * Вопрос не найден.
 */
public final class CrosswordQuestionNotFoundException extends CrosswordException {

  private static final String MSG_KEY = "puzzle.crossword.error.validation.question.not.found";

  public CrosswordQuestionNotFoundException(String questionId) {
    super(I18n.of(MSG_KEY, questionId));
  }
}
