package ru.rshb.intech.digital.feature.contests.chef.exception;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.messages.I18n;

public class ChefContestValidationException extends ChefContestException {

  public ChefContestValidationException(String message) {
    super(message);
  }

  public ChefContestValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Некорректное число очков.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException invalidScoresAmount() {
    return new AttributeBusinessRulesException(
        I18n.of("contest.chef.error.validation.scores.invalid.amount")
    );
  }

  /**
   * Количество очков не совпадает.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException scoresNotMatch() {
    return new AttributeBusinessRulesException(
        I18n.of("contest.chef.error.validation.scores.not.match")
    );
  }

  /**
   * Участник с такими данными уже зарегистрирован.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException contestantAlreadyExists() {
    return new AttributeBusinessRulesException(
        I18n.of("contest.chef.error.participant.already.registered")
    );
  }
}
