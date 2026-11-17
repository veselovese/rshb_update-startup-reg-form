package ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.phone.PhoneAttributeBase;
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowDemandFormDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowDemand;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.WindowDemandForm;

/**
 * Атрибут "Номер телефона.".
 * Форма запроса на инновации.
 */
@Component("windowDemandPhone")
@FormAttribute(WindowDemandForm.class)
public class Phone extends PhoneAttributeBase<InnovationWindowDemandFormDto, InnovationWindowDemand> {

  private static final int PROCESS_ORDER = 2;

  @Override
  public String supplyInputValue(@Nonnull InnovationWindowDemandFormDto inputData) {
    return inputData.getPhone();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull InnovationWindowDemand outputData) {
    outputData.setPhone(outputValue);
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }
}
