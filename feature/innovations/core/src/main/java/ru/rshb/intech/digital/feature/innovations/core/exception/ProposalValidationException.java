package ru.rshb.intech.digital.feature.innovations.core.exception;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.messages.I18n;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProposalValidationException {

  /**
   * Запрос на инновации не найден: {0}
   *
   * @param demandCardId ID карточки с запросом инновации.
   * @return Исключение.
   */
  public static AttributeBusinessRulesException demandNotFound(@Nonnull String demandCardId) {
    return new AttributeBusinessRulesException(
        I18n.of("innovations.demand.error.not.found", demandCardId)
    );
  }
}
