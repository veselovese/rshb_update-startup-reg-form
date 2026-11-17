package ru.rshb.intech.digital.puzzles.core.crossword.exception;

import ru.rshb.intech.digital.messages.I18n;

/**
 * Кроссворд не найден.
 */
public final class CrosswordNotFoundException extends CrosswordException {

  private static final String MSG_KEY = "puzzle.crossword.error.validation.crossword.not.found";

  public CrosswordNotFoundException(String crosswordId) {
    super(I18n.of(MSG_KEY, crosswordId));
  }
}
