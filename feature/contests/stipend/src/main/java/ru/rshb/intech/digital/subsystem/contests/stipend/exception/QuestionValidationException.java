package ru.rshb.intech.digital.subsystem.contests.stipend.exception;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.messages.I18n;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionValidationException {

  /**
   * Превышен лимит на число вопросов от одного пользователя
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException maxQuestionsPerUserExceeded() {
    return new AttributeBusinessRulesException(
        I18n.of("contest.stipend.question.validation.error.question.number.exceeded")
    );
  }

}
