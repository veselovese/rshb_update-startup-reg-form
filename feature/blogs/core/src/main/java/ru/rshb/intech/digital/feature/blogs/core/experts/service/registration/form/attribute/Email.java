package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.email.EmailAttributeBase;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationFormData;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.ExpertRegistrationForm;

/**
 * Атрибут "Электронная почта".
 * Форма регистрации "Стать экспертом".
 */
@Component("expertRegistrationFormEmail")
@FormAttribute(ExpertRegistrationForm.class)
public class Email extends EmailAttributeBase<ExpertRegistrationFormData, ExpertRegistration> {

  @Override
  public String supplyInputValue(@Nonnull ExpertRegistrationFormData inputData) {
    return inputData.getEmail();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull ExpertRegistration outputData) {
    outputData.setEmail(outputValue);
  }
}
