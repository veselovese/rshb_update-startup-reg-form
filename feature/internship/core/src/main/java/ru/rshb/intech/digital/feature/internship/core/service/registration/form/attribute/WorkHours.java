package ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
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
import ru.rshb.intech.digital.feature.internship.core.service.hours.WorkHoursService;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.InternshipRegistrationForm;

@Component("internshipRegistrationWorkHours")
@FormAttribute(InternshipRegistrationForm.class)
public class WorkHours extends TextAttributeBase<InternshipRegistrationDto, Registration> {

  public static final String TITLE_KEY = "internship.registration.form.attribute.pref-hours-per-week";

  public WorkHours(@Nonnull WorkHoursService workHoursService) {
    super(TITLE_KEY, Registration.PREF_HOURS_LEN, new PrefHoursBusinessRulesValidator(workHoursService));
  }

  @Override
  public String supplyInputValue(@Nonnull InternshipRegistrationDto inputData) {
    return inputData.getPrefHrsWk();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull Registration outputData) {
    outputData.setWorkHours(outputValue);
  }

  @RequiredArgsConstructor
  private static class PrefHoursBusinessRulesValidator
      implements BusinessRulesValidator<InternshipRegistrationDto, Registration, String> {

    private final WorkHoursService workHoursService;

    @Override
    public void validate(String value, @Nonnull FormContext<InternshipRegistrationDto, Registration> context)
        throws AttributeBusinessRulesException {

      if (workHoursService.isWorkHoursNotExist(value)) {
        throw RegistrationValidationException.invalidWorkHours();
      }
    }
  }
}
