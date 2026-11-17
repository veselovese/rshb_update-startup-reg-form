package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.phone.PhoneAttributeBase;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationFormData;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.ExpertRegistrationForm;

/**
 * Атрибут "Номер телефона".
 * Форма регистрации "Стать экспертом".
 */
@Component("expertRegistrationFormPhone")
@FormAttribute(ExpertRegistrationForm.class)
public class Phone extends PhoneAttributeBase<ExpertRegistrationFormData, ExpertRegistration> {

  @Override
  public String supplyInputValue(@Nonnull ExpertRegistrationFormData inputData) {
    return inputData.getPhone();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull ExpertRegistration outputData) {
    outputData.setPhone(outputValue);
  }
}
