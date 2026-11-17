package ru.rshb.intech.digital.feature.innovations.core.exception;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.messages.I18n;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeneralRequestValidationException {

  /**
   * Заявка с такими данными уже существует.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException requestAlreadyExist() {
    return new AttributeBusinessRulesException(
        I18n.of("innovations.general.request.error.validation.duplicate")
    );
  }

}
