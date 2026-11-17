package ru.rshb.intech.digital.feature.startups.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.vk.VkUrlAttributeBase;
import ru.rshb.intech.digital.feature.startups.core.service.registration.form.StartupRegistrationForm;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegistrationDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;

/**
 * Атрибут "Ссылка на профиль VK".
 * Форма заявки на включение стартапа в витрину.
 */
@Component("startupRegFormVkUrl")
@FormAttribute(StartupRegistrationForm.class)
public class VkProfileUrl extends VkUrlAttributeBase<StartupRegistrationDto, StartupRegistration> {


  @Override
  public String supplyInputValue(@Nonnull StartupRegistrationDto inputData) {
    return inputData.getVkProfileUrl();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull StartupRegistration outputData) {
    outputData.setVkProfileUrl(outputValue);
  }
}
