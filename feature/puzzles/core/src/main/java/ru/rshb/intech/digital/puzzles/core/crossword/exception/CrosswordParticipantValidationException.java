package ru.rshb.intech.digital.puzzles.core.crossword.exception;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.messages.I18n;

public class CrosswordParticipantValidationException extends CrosswordException {

  public CrosswordParticipantValidationException(String messageKey) {
    super(I18n.of(messageKey));
  }

  public CrosswordParticipantValidationException(@Nonnull String messagePatternKey, @Nonnull Object... values) {
    super(I18n.of(messagePatternKey, values));
  }

  /**
   * Запись уже существует.
   *
   * @return Исключение.
   */
  @Nonnull
  public static CrosswordParticipantValidationException entryAlreadyExists() {
    return new CrosswordParticipantValidationException(
        "puzzle.crossword.error.validation.scores.already.exists"
    );
  }

  /**
   * Не указан результат.
   *
   * @return Исключение.
   */
  @Nonnull
  public static CrosswordParticipantValidationException resultMissing() {
    return new CrosswordParticipantValidationException(
        "puzzle.crossword.error.validation.scores.result.missing"
    );
  }

  /**
   * Некорректное значение результата.
   *
   * @return Исключение.
   */
  @Nonnull
  public static CrosswordParticipantValidationException invalidResult() {
    return new CrosswordParticipantValidationException(
        "puzzle.crossword.error.validation.scores.result.invalid"
    );
  }

  /**
   * Имя не должно быть пустым.
   *
   * @return Исключение.
   */
  @Nonnull
  public static CrosswordParticipantValidationException participantNameMissing() {
    return new CrosswordParticipantValidationException(
        "puzzle.crossword.error.validation.scores.participant.name.missing"
    );
  }

  /**
   * Некорректное имя участника.
   *
   * @return Исключение.
   */
  @Nonnull
  public static CrosswordParticipantValidationException invalidParticipantName() {
    return new CrosswordParticipantValidationException(
        "puzzle.crossword.error.validation.scores.participant.name.bad"
    );
  }

  /**
   * Некорректный ID сессии.
   *
   * @return Исключение.
   */
  @Nonnull
  public static CrosswordParticipantValidationException invalidSessionId() {
    return new CrosswordParticipantValidationException(
        "puzzle.crossword.error.validation.scores.no.session.id"
    );
  }

  /**
   * Не указан кроссворд.
   *
   * @return Исключение.
   */
  @Nonnull
  public static CrosswordParticipantValidationException crosswordMissing() {
    return new CrosswordParticipantValidationException(
        "puzzle.crossword.error.validation.scores.no.crossword"
    );
  }
}
