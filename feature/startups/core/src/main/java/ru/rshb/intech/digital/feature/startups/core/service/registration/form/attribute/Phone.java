package ru.rshb.intech.digital.feature.startups.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.phone.PhoneAttributeBase;
import ru.rshb.intech.digital.feature.startups.core.service.registration.form.StartupRegistrationForm;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegistrationDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;

/**
 * Атрибут "Номер телефона".
 * Форма заявки на включение стартапа в витрину.
 */
@Component("startupRegFormPhone")
@FormAttribute(StartupRegistrationForm.class)
public class Phone extends PhoneAttributeBase<StartupRegistrationDto, StartupRegistration> {

  @Override
  public String supplyInputValue(@Nonnull StartupRegistrationDto inputData) {
    return inputData.getPhone();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull StartupRegistration outputData) {
    outputData.setPhone(outputValue);
  }
}
