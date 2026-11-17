package ru.rshb.intech.digital.feature.startups.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.number.IntegerAttributeBase;
import ru.rshb.intech.digital.feature.startups.core.service.registration.form.StartupRegistrationForm;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegistrationDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;

/**
 * Атрибут "Количество сотрудников".
 * Форма заявки на включение стартапа в витрину.
 */
@Component("startupRegFormStaffSize")
@FormAttribute(StartupRegistrationForm.class)
public class StaffSize extends IntegerAttributeBase<StartupRegistrationDto, StartupRegistration> {

  public static final String TITLE_KEY = "startups.registration.attr.staff.size.name";
  public static final int MIN_VALUE = 0;
  public static final int MAX_VALUE = Integer.MAX_VALUE;

  public StaffSize() {
    super(TITLE_KEY, MIN_VALUE, MAX_VALUE, true);
  }

  @Override
  public Integer supplyInputValue(@Nonnull StartupRegistrationDto inputData) {
    return inputData.getStaffSize();
  }

  @Override
  public void consumeOutputValue(Integer outputValue, @Nonnull StartupRegistration outputData) {
    outputData.setStaffSize(outputValue);
  }
}
