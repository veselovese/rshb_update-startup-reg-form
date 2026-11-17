package ru.rshb.intech.digital.feature.innovations.core.service.general.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.email.EmailAttributeBase;
import ru.rshb.intech.digital.feature.innovations.core.dto.GeneralRequestDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.GeneralRequest;
import ru.rshb.intech.digital.feature.innovations.core.service.general.form.GeneralRequestForm;

/**
 * Атрибут "Электронная почта".
 * Форма заявки с предложением или запросом инноваций.
 */
@Component("innovationsGeneralRequestFormEmail")
@FormAttribute(GeneralRequestForm.class)
public class Email extends EmailAttributeBase<GeneralRequestDto, GeneralRequest> {

  private static final int PROCESS_ORDER = 2;

  @Override
  public String supplyInputValue(@Nonnull GeneralRequestDto inputData) {
    return inputData.getEmail();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull GeneralRequest outputData) {
    outputData.setEmail(outputValue);
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }
}
