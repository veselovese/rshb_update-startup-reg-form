package ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.exception.RegistrationValidationException;
import ru.rshb.intech.digital.feature.internship.core.repository.fields.InternshipFieldRepository;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.InternshipRegistrationForm;

import java.util.Arrays;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.apache.commons.lang3.StringUtils.split;

/**
 * Атрибут "Направления стажировки".
 * Форма подачи заявки на стажировку.
 */
@Component("internshipRegistrationFields")
@FormAttribute(InternshipRegistrationForm.class)
public class InternshipFields extends TextAttributeBase<InternshipRegistrationDto, Registration> {

  public static final String TITLE_KEY = "internship.registration.form.attribute.fields";

  public InternshipFields(@Nonnull InternshipFieldRepository fieldRepository) {
    super(TITLE_KEY, Registration.FIELD_MAX_LEN, new FieldValidator(fieldRepository));
  }

  @Override
  public String supplyInputValue(@Nonnull InternshipRegistrationDto inputData) {
    return inputData.getInternshipField();
  }

  @Override
  public void consumeOutputValue(@Nullable String value, @Nonnull Registration outputData) {
    outputData.setInternshipField(value);
  }


  @RequiredArgsConstructor
  private static class FieldValidator
      implements BusinessRulesValidator<InternshipRegistrationDto, Registration, String> {

    private final InternshipFieldRepository fieldRepository;

    @Override
    public void validate(String value,
                         @Nonnull FormContext<InternshipRegistrationDto, Registration> context)
        throws AttributeBusinessRulesException {

      final var fields = split(value, ",");
      final var fieldsSet = Arrays.stream(fields).collect(toSet());

      if (isFieldsNotExist(fieldsSet)) {
        throw RegistrationValidationException.invalidInternshipDirection();
      }
    }

    private boolean isFieldsNotExist(@Nonnull Set<String> fieldTitles) {
      return !fieldRepository.existsAllByTitleIsIn(fieldTitles);
    }
  }
}
