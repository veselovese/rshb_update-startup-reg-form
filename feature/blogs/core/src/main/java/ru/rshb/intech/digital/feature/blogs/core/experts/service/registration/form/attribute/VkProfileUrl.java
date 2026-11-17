package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.vk.VkUrlAttributeBase;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationFormData;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.ExpertRegistrationForm;

/**
 * Атрибут "Ссылка на профиль VK".
 * Форма регистрации "Стать экспертом".
 */
@Component("expertRegistrationFormVkProfileUrl")
@FormAttribute(ExpertRegistrationForm.class)
public class VkProfileUrl extends VkUrlAttributeBase<ExpertRegistrationFormData, ExpertRegistration> {

  @Override
  public String supplyInputValue(@Nonnull ExpertRegistrationFormData inputData) {
    return inputData.getVkProfileUrl();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull ExpertRegistration outputData) {
    outputData.setVkUrl(outputValue);
  }
}
