package ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.phone.PhoneAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.exception.RegistrationValidationException;
import ru.rshb.intech.digital.feature.internship.core.repository.registration.RegistrationRepository;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.InternshipRegistrationForm;

/**
 * Атрибут "Номер телефона".
 * Форма подачи заявки на стажировку.
 */
@Component("internshipRegistrationPhone")
@FormAttribute(InternshipRegistrationForm.class)
public class Phone extends PhoneAttributeBase<InternshipRegistrationDto, Registration> {


  public Phone(@Nonnull RegistrationRepository registrationRepository) {
    super(new DuplicateValidator(registrationRepository));
  }


  @Override
  public String supplyInputValue(@Nonnull InternshipRegistrationDto inputData) {
    return inputData.getPhone();
  }

  @Override
  public void consumeOutputValue(String value, @Nonnull Registration outputData) {
    outputData.setPhone(value);
  }


  @RequiredArgsConstructor
  private static class DuplicateValidator
      implements BusinessRulesValidator<InternshipRegistrationDto, Registration, String> {

    private final RegistrationRepository registrationRepository;

    @Override
    public void validate(String phone,
                         @Nonnull FormContext<InternshipRegistrationDto, Registration> context)
        throws AttributeBusinessRulesException {

      if (registrationRepository.existsRegistrationByPhone(phone)) {
        throw RegistrationValidationException.applicationWithPhoneAlreadyExist();
      }
    }
  }
}
