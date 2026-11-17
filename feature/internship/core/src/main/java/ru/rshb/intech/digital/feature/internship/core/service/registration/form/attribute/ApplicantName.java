package ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.applicant.ApplicantNameAttributeBase;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.InternshipRegistrationForm;

/**
 * Атрибут "ФИО".
 * Форма подачи заявки на стажировку.
 */
@Component("internshipRegistrationApplicantName")
@FormAttribute(InternshipRegistrationForm.class)
public class ApplicantName extends ApplicantNameAttributeBase<InternshipRegistrationDto, Registration> {

  @Nullable
  @Override
  public String supplyInputValue(@Nonnull InternshipRegistrationDto inputData) {
    return inputData.getApplicantName();
  }

  @Override
  public void consumeOutputValue(@Nullable String value, @Nonnull Registration outputData) {
    outputData.setApplicantName(value);
  }
}
