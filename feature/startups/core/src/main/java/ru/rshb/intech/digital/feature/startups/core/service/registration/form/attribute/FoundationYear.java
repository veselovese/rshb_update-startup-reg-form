package ru.rshb.intech.digital.feature.startups.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.number.IntegerAttributeBase;
import ru.rshb.intech.digital.feature.startups.core.service.registration.form.StartupRegistrationForm;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegistrationDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;

import java.time.LocalDateTime;

/**
 * Атрибут "Год основания стартапа".
 * Форма заявки на включение стартапа в витрину.
 */
@Component("startupRegFormFoundYear")
@FormAttribute(StartupRegistrationForm.class)
public class FoundationYear extends IntegerAttributeBase<StartupRegistrationDto, StartupRegistration> {

  public static final String TITLE_KEY = "startups.registration.attr.foundation.year.name";
  private static final int MIN_VALUE = 2000;

  public FoundationYear() {
    super(TITLE_KEY, MIN_VALUE, LocalDateTime.now().getYear());
  }

  @Override
  public Integer supplyInputValue(@Nonnull StartupRegistrationDto inputData) {
    return inputData.getFoundedYear();
  }

  @Override
  public void consumeOutputValue(Integer outputValue, @Nonnull StartupRegistration outputData) {
    outputData.setFoundationYear(outputValue);
  }
}
