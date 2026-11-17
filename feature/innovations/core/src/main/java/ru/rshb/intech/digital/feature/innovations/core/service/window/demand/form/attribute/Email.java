package ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.email.EmailAttributeBase;
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowDemandFormDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowDemand;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.WindowDemandForm;

/**
 * Атрибут "Электронная почта".
 * Форма запроса на инновации.
 */
@Component("windowDemandEmail")
@FormAttribute(WindowDemandForm.class)
public class Email extends EmailAttributeBase<InnovationWindowDemandFormDto, InnovationWindowDemand> {

  private static final int PROCESS_ORDER = 1;


  @Override
  public String supplyInputValue(@Nonnull InnovationWindowDemandFormDto inputData) {
    return inputData.getEmail();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull InnovationWindowDemand outputData) {
    outputData.setEmail(outputValue);
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }
}
