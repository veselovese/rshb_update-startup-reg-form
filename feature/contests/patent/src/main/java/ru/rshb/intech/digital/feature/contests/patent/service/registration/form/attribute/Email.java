package ru.rshb.intech.digital.feature.contests.patent.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.email.EmailAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.commons.forms.exception.common.RequestNumberExceededException;
import ru.rshb.intech.digital.feature.contests.patent.dto.PatentContestRegistrationRequestDto;
import ru.rshb.intech.digital.feature.contests.patent.entity.PatentContestRegistration;
import ru.rshb.intech.digital.feature.contests.patent.service.PatentContestRegistrationService;
import ru.rshb.intech.digital.feature.contests.patent.service.registration.form.PatentContestRegistrationForm;

/**
 * Атрибут "Электронная почта".
 * Форма регистрации участника в конкурсе патентов.
 */
@Component
@FormAttribute(PatentContestRegistrationForm.class)
public class Email extends EmailAttributeBase<PatentContestRegistrationRequestDto, PatentContestRegistration> {

  public Email(PatentContestRegistrationService registrationService) {
    super(new EmailValidator(registrationService));
  }

  @Override
  public String supplyInputValue(@Nonnull PatentContestRegistrationRequestDto inputData) {
    return inputData.getEmail();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentContestRegistration outputData) {
    outputData.setEmail(outputValue);
  }


  @RequiredArgsConstructor
  private static class EmailValidator
      implements BusinessRulesValidator<PatentContestRegistrationRequestDto, PatentContestRegistration, String> {

    public static final long MAX_REGS_PER_EMAIL = 3;

    private final PatentContestRegistrationService registrationService;

    @Override
    public void validate(String value,
                         @Nonnull FormContext<PatentContestRegistrationRequestDto, PatentContestRegistration> context)
        throws AttributeBusinessRulesException {

      final long existingRegsCount = registrationService.getRegistrationCountByEmail(value);
      if (existingRegsCount >= MAX_REGS_PER_EMAIL) {
        throw new RequestNumberExceededException();
      }
    }
  }
}
