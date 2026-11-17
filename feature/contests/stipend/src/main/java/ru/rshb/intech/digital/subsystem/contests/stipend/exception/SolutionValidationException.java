package ru.rshb.intech.digital.subsystem.contests.stipend.exception;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.messages.I18n;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SolutionValidationException {

  /**
   * Превышен лимит на число решений от одного пользователя
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException maxSolutionsPerUserExceeded() {
    return new AttributeBusinessRulesException(
        I18n.of("contest.stipend.solution.validation.error.solution.number.exceeded")
    );
  }
}
