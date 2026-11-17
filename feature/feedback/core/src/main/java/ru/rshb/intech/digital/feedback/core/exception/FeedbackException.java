package ru.rshb.intech.digital.feedback.core.exception;

import jakarta.annotation.Nonnull;
import lombok.Generated;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.commons.utils.exceptiion.DigitalServiceException;
import ru.rshb.intech.digital.messages.I18n;

@Generated
public class FeedbackException extends DigitalServiceException {

  public FeedbackException(String message) {
    super(message);
  }

  /**
   * Превышено число заявок за {0} ч
   *
   * @param hours Интервал (часы).
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException requestLimitException(int hours) {
    return new AttributeBusinessRulesException(
        I18n.of("feedback.report.error.validation.limit.exceeded", hours)
    );
  }
}
