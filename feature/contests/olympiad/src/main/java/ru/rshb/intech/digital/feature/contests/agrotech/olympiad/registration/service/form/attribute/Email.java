package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.email.EmailAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.commons.forms.exception.common.RequestNumberExceededException;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.AgrotechOlympiadRegistrationDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadRegistration;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.AgrotechOlympiadRegistrationService;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.AgrotechOlympiadRegistrationForm;

/**
 * Атрибут "Электронная почта".
 * Форма заявки на участие в олимпиаде "Навстречу Агротеху".
 */
@Component("agrotechOlympiadRegistrationEmail")
@FormAttribute(AgrotechOlympiadRegistrationForm.class)
public class Email
    extends EmailAttributeBase<AgrotechOlympiadRegistrationDto, AgrotechOlympiadRegistration>{

  private static final int PROCESS_ORDER = 1;


  public Email(AgrotechOlympiadRegistrationService registrationService) {
    super(new DefaultBusinessRulesValidator(registrationService));
  }

  @Override
  public String supplyInputValue(AgrotechOlympiadRegistrationDto inputData) {
    return inputData.email();
  }

  @Override
  public void consumeOutputValue(String outputValue, AgrotechOlympiadRegistration outputData) {
    outputData.setEmail(outputValue);
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }

  @RequiredArgsConstructor
  private static class DefaultBusinessRulesValidator
      implements BusinessRulesValidator<AgrotechOlympiadRegistrationDto, AgrotechOlympiadRegistration, String> {

    private final AgrotechOlympiadRegistrationService registrationService;

    @Override
    public void validate(String email,
                         @Nonnull FormContext<AgrotechOlympiadRegistrationDto, AgrotechOlympiadRegistration> context)
        throws AttributeBusinessRulesException {

      if (!registrationService.isRegistrationAllowed(email)) {
        throw new RequestNumberExceededException();
      }
    }
  }
 }
