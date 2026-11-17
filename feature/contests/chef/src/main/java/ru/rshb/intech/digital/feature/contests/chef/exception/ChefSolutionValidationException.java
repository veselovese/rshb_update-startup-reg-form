package ru.rshb.intech.digital.feature.contests.chef.exception;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.messages.I18n;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChefSolutionValidationException {

  /**
   * URL возвращает некорректные данные.
   *
   * @return Исключение.
   */
  public static ChefContestUrlCheckException solutionUrlBadResponse() {
    return new ChefContestUrlCheckException(
        I18n.of("contest.chef.error.validation.solution.url.bad.result")
    );
  }

  /**
   * Некорректный формат URL.
   *
   * @return Исключение.
   */
  @Nonnull
  public static ChefContestUrlCheckException invalidSolutionUrl() {
    return new ChefContestUrlCheckException(
        I18n.of("contest.chef.error.validation.solution.url.format")
    );
  }


  /**
   * Ресурс недоступен по данному URL.
   *
   * @param throwable Исходное исключение.
   * @return Исключение.
   */
  @Nonnull
  public static ChefContestUrlCheckException solutionUnavailable(@Nonnull Throwable throwable) {
    return new ChefContestUrlCheckException(
        I18n.of("contest.chef.error.validation.solution.url.unavailable"),
        throwable
    );
  }

  /**
   *
   * @param throwable Исходное исключение.
   * @return Исключение.
   */
  @Nonnull
  public static ChefContestUrlCheckException solutionCheckError(@Nonnull Throwable throwable) {
    return new ChefContestUrlCheckException(
        I18n.of("contest.chef.error.validation.solution.result.check"),
        throwable
    );
  }

}
