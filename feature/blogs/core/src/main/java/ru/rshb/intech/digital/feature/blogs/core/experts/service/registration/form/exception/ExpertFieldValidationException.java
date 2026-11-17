package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.exception;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.messages.I18n;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExpertFieldValidationException {

  /**
   * Не выбрана или не указана экспертная область.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException valueMissing() {
    return new AttributeBusinessRulesException(
        I18n.of("expert.registration.form.validation.error.applicant.field.missing")
    );
  }

  /**
   * Некорректное значение экспертной области.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException invalidValue() {
    return new AttributeBusinessRulesException(
        I18n.of("expert.registration.form.validation.error.applicant.field.bad.value")
    );
  }

}
