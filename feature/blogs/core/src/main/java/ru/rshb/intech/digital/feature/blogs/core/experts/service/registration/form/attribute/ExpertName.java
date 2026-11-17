package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.applicant.ApplicantNameAttributeBase;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationFormData;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.ExpertRegistrationForm;

/**
 * Атрибут "ФИО".
 * Форма регистрации "Стать экспертом".
 */
@Component("expertRegistrationFormExpertName")
@FormAttribute(ExpertRegistrationForm.class)
public class ExpertName extends ApplicantNameAttributeBase<ExpertRegistrationFormData, ExpertRegistration> {

  @Override
  public String supplyInputValue(@Nonnull ExpertRegistrationFormData inputData) {
    return inputData.getExpertName();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull ExpertRegistration outputData) {
    outputData.setExpertName(outputValue);
  }
}
