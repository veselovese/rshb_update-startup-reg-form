package ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowDemandFormDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowDemand;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.WindowDemandForm;

/**
 * Атрибут "Полное описание".
 * Форма запроса на инновации.
 */
@Component("windowDemandDescription")
@FormAttribute(WindowDemandForm.class)
public class Description extends TextAttributeBase<InnovationWindowDemandFormDto, InnovationWindowDemand> {

  public static final String TITLE_KEY = "innovations.demand.form.attribute.description";


  public Description() {
    super(TITLE_KEY, InnovationWindowDemand.DESCRIPTION_LENGTH);
  }


  @Override
  public String supplyInputValue(@Nonnull InnovationWindowDemandFormDto inputData) {
    return inputData.getDescription();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull InnovationWindowDemand outputData) {
    outputData.setDescription(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
