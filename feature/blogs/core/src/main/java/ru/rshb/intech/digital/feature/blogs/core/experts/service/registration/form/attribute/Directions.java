package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextListAttributeBase;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextListAttributeParams;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationFormData;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.ExpertFieldService;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.ExpertRegistrationForm;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.exception.ExpertFieldValidationException;
import ru.rshb.intech.digital.messages.I18n;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.split;

/**
 * Атрибут "Экспертные области блогера".
 * Форма регистрации "Стать экспертом".
 */
@Component("expertRegistrationFormDirections")
@FormAttribute(ExpertRegistrationForm.class)
public class Directions
    extends TextListAttributeBase<ExpertRegistrationFormData, ExpertRegistration, String> {

  public static final String TITLE_KEY = "expert.registration.form.attribute.directions";
  public static final int MAX_NUMBER = 3;


  public Directions(@Nonnull ExpertFieldService fieldService) {
    super(
        TextListAttributeParams.Builder
            .<ExpertRegistrationFormData, ExpertRegistration, String>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(false)
            .maxSize(MAX_NUMBER)
            .businessRulesValidator(new FieldValidator(fieldService))
            .build()
    );
  }

  @Override
  public List<String> supplyInputValue(@Nonnull ExpertRegistrationFormData inputData) {
    return inputData.getDirections();
  }

  @Override
  public void consumeOutputValue(String commaSeparatedDirections, @Nonnull ExpertRegistration outputData) {
    outputData.setDirections(commaSeparatedDirections);
  }

  @Override
  public <D, E> String map(List<String> rawValue, @Nonnull FormContext<D, E> context) {
    return StringUtils.join(rawValue, ",");
  }

  @RequiredArgsConstructor
  private static class FieldValidator
      implements BusinessRulesValidator<ExpertRegistrationFormData, ExpertRegistration, String> {

    private final ExpertFieldService fieldService;

    @Override
    public void validate(String fieldList,
                         @Nonnull FormContext<ExpertRegistrationFormData, ExpertRegistration> context)
        throws AttributeBusinessRulesException {

      final var notBlankTitles = Arrays.stream(split(fieldList, ","))
          .filter(StringUtils::isNotBlank)
          .map(StringUtils::trim)
          .collect(Collectors.toSet());

      if (notBlankTitles.isEmpty()) {
        throw ExpertFieldValidationException.valueMissing();
      }

      // Из всех переданных значений может отсутствовать в справочнике только одно значение.
      var customValueAlreadyPresents = false;

      for (final var field : notBlankTitles) {

        final var fieldExist = fieldService.isFieldExist(field);
        if (fieldExist) {
          continue;
        }

        if (customValueAlreadyPresents) {
          throw ExpertFieldValidationException.invalidValue();
        }
        customValueAlreadyPresents = true;
      }
    }
  }
}
