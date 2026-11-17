package ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowDemandFormDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowDemand;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.WindowDemandForm;

/**
 * Атрибут "Название компании".
 * Форма запроса на инновации.
 */
@Component("windowDemandCompany")
@FormAttribute(WindowDemandForm.class)
public class Company extends TextAttributeBase<InnovationWindowDemandFormDto, InnovationWindowDemand> {

  public static final String TITLE_KEY = "innovations.demand.form.attribute.company.name";


  public Company() {
    super(TITLE_KEY, InnovationWindowDemand.COMPANY_NAME_LENGTH);
  }


  @Override
  public String supplyInputValue(@Nonnull InnovationWindowDemandFormDto inputData) {
    return inputData.getCompany();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull InnovationWindowDemand outputData) {
    outputData.setCompanyName(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
