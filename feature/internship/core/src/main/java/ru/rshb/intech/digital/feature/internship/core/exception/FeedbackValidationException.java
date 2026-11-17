package ru.rshb.intech.digital.feature.internship.core.exception;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;

import static ru.rshb.intech.digital.messages.I18n.of;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FeedbackValidationException {

  /**
   * Превышено максимальное количество отзывов.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException feedbackReportNumberExceeded() {
    return new AttributeBusinessRulesException(
        of("internship.feedback.validation.error.report.number.exceeded")
    );
  }
}
