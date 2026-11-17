package ru.rshb.intech.digital.feature.startups.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.startups.core.service.registration.form.StartupRegistrationForm;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegistrationDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;

/**
 * Атрибут "Должность".
 * Форма заявки на включение стартапа в витрину.
 */
@Component("startupRegFormPosition")
@FormAttribute(StartupRegistrationForm.class)
public class Position extends TextAttributeBase<StartupRegistrationDto, StartupRegistration> {

  public static final String TITLE_KEY = "startups.registration.attr.position.name";
  private static final int MAX_LENGTH = 255;

  public Position() {
    super(TITLE_KEY, true, MAX_LENGTH);
  }

  @Override
  public String supplyInputValue(@Nonnull StartupRegistrationDto inputData) {
    return inputData.getPosition();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull StartupRegistration outputData) {
    outputData.setPosition(outputValue);
  }
}
