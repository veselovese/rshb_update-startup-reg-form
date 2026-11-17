package ru.rshb.intech.digital.feature.startups.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.startups.core.service.registration.form.StartupRegistrationForm;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegistrationDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;

/**
 * Атрибут "Полное описание".
 * Форма заявки на включение стартапа в витрину.
 */
@Component("startupRegFormDescFull")
@FormAttribute(StartupRegistrationForm.class)
public class DescriptionFull extends TextAttributeBase<StartupRegistrationDto, StartupRegistration> {

  public static final String TITLE_KEY = "startups.registration.attr.description.full.name";
  private static final int MAX_LENGTH = 1000;

  public DescriptionFull() {
    super(TITLE_KEY, MAX_LENGTH);
  }

  @Override
  public String supplyInputValue(@Nonnull StartupRegistrationDto inputData) {
    return inputData.getDescriptionFull();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull StartupRegistration outputData) {
    outputData.setProjectDescriptionFull(outputValue);
  }
}
