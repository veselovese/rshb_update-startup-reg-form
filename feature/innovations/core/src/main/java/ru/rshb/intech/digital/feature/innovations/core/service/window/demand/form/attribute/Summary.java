package ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowDemandFormDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowDemand;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.WindowDemandForm;

/**
 * Атрибут "Краткое описание".
 * Форма запроса на инновации.
 */
@Component("windowDemandSummary")
@FormAttribute(WindowDemandForm.class)
public class Summary extends TextAttributeBase<InnovationWindowDemandFormDto, InnovationWindowDemand> {

  public static final String TITLE_KEY = "innovations.demand.form.attribute.summary";


  public Summary() {
    super(TITLE_KEY, InnovationWindowDemand.SUMMARY_LENGTH);
  }


  @Override
  public String supplyInputValue(@Nonnull InnovationWindowDemandFormDto inputData) {
    return inputData.getSummary();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull InnovationWindowDemand outputData) {
    outputData.setSummary(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
