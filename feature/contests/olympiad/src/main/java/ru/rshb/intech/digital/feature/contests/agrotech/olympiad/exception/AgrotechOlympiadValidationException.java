package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.exception;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.messages.I18n;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AgrotechOlympiadValidationException {

  /**
   * Превышен лимит на число вопросов от одного пользователя.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException questionLimitExceeded() {
    return new AttributeBusinessRulesException(
        I18n.of("agrotech.olympiad.question.validation.error.questions.limit.exceeded")
    );
  }

  /**
   * ВУЗ с указанным ID не найден.
   *
   * @param universityId ID ВУЗ'а.
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException universityNotFound(String universityId) {
    return new AttributeBusinessRulesException("University not found: " + universityId);
  }

}
