package ru.rshb.intech.digital.feature.startups.core.service.registration.form.attribute;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.number.LongAttributeBase;
import ru.rshb.intech.digital.feature.startups.core.service.registration.form.StartupRegistrationForm;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegistrationDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;

/**
 * Атрибут "Объем инвестиций".
 * Форма заявки на включение стартапа в витрину.
 */
@Component("startupRegFormInvestValue")
@FormAttribute(StartupRegistrationForm.class)
public class InvestmentsValue extends LongAttributeBase<StartupRegistrationDto, StartupRegistration> {

  public static final String TITLE_KEY = "startups.registration.attr.investments.value.name";
  private static final long MIN_VALUE = 0;
  private static final long MAX_VALUE = 999_999_999_999L;

  public InvestmentsValue() {
    super(TITLE_KEY, true, MIN_VALUE, MAX_VALUE);
  }

  @Override
  public Long supplyInputValue(StartupRegistrationDto inputData) {
    return inputData.getInvestmentsValue();
  }

  @Override
  public void consumeOutputValue(Long outputValue, StartupRegistration outputData) {
    outputData.setInvestmentsValue(outputValue);
  }
}
