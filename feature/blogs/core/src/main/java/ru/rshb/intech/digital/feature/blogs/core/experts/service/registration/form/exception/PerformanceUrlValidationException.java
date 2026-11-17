package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.exception;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.messages.I18n;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PerformanceUrlValidationException {

  /**
   * Некорректный формат ссылки на выступление.
   *
   * @return Исключение.
   */
  @Nonnull
  public static AttributeBusinessRulesException badFormat() {
    return new AttributeBusinessRulesException(
        I18n.of("expert.registration.form.validation.error.applicant.performance.urls.format")
    );
  }

}
