package ru.rshb.intech.digital.feature.startups.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.applicant.ApplicantNameAttributeBase;
import ru.rshb.intech.digital.feature.startups.core.service.registration.form.StartupRegistrationForm;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegistrationDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;

/**
 * Атрибут "ФИО".
 * Форма заявки на включение стартапа в витрину.
 */
@Component("startupRegFormApplicantName")
@FormAttribute(StartupRegistrationForm.class)
public class ApplicantName extends ApplicantNameAttributeBase<StartupRegistrationDto, StartupRegistration> {

  @Override
  public String supplyInputValue(@Nonnull StartupRegistrationDto inputData) {
    return inputData.getApplicantName();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull StartupRegistration outputData) {
    outputData.setApplicantName(outputValue);
  }
}
