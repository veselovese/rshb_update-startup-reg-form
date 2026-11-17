package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextListAttributeBase;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextListAttributeParams;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.commons.validation.url.UrlUtils;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationFormData;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.ExpertRegistrationForm;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.exception.PerformanceUrlValidationException;
import ru.rshb.intech.digital.messages.I18n;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.split;

/**
 * Атрибут "Ссылки на выступление".
 * Форма регистрации "Стать экспертом".
 */
@Component("expertRegistrationFormPerformanceUrls")
@FormAttribute(ExpertRegistrationForm.class)
public class PerformanceUrls
    extends TextListAttributeBase<ExpertRegistrationFormData, ExpertRegistration, String> {

  public static final String TITLE_KEY = "expert.registration.form.attribute.performance.urls";
  public static final int MAX_NUMBER = 5;


  public PerformanceUrls() {
    super(
        TextListAttributeParams.Builder
            .<ExpertRegistrationFormData, ExpertRegistration, String>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(true)
            .maxSize(MAX_NUMBER)
            .businessRulesValidator(new PerformanceUrlValidator())
            .build()
    );
  }

  @Override
  public List<String> supplyInputValue(@Nonnull ExpertRegistrationFormData inputData) {
    return inputData.getPerformanceUrls();
  }

  @Override
  public void consumeOutputValue(String commaSeparatedUrls, @Nonnull ExpertRegistration outputData) {
    outputData.setPerformanceUrls(
        defaultIfBlank(commaSeparatedUrls, null)
    );
  }

  @Override
  public <D, E> String map(List<String> rawValue, @Nonnull FormContext<D, E> context) {
    return StringUtils.join(rawValue, ",");
  }

  private static class PerformanceUrlValidator
      implements BusinessRulesValidator<ExpertRegistrationFormData, ExpertRegistration, String> {

    @Override
    public void validate(String urls,
                         @Nonnull FormContext<ExpertRegistrationFormData, ExpertRegistration> context)
        throws AttributeBusinessRulesException {

      if (isBlank(urls)) {
        return;
      }

      final var notBlankUrls = Arrays.stream(split(urls, ","))
          .filter(StringUtils::isNotBlank)
          .map(StringUtils::trim)
          .collect(Collectors.toSet());

      final var allValidUrls = notBlankUrls.stream()
          .allMatch(UrlUtils::isValidUrl);

      if (allValidUrls) {
        return;
      }

      throw PerformanceUrlValidationException.badFormat();
    }
  }
}
