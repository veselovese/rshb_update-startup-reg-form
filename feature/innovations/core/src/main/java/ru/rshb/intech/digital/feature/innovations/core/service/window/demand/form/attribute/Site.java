package ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowDemandFormDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowDemand;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.WindowDemandForm;

/**
 * Атрибут "Ссылка на сайт".
 * Форма запроса на инновации.
 */
@Component("windowDemandSite")
@FormAttribute(WindowDemandForm.class)
public class Site extends TextAttributeBase<InnovationWindowDemandFormDto, InnovationWindowDemand> { // TODO унаследоваться от UrlAttributeBase

  public static final String TITLE_KEY = "innovations.demand.form.attribute.company.site";


  public Site() {
    super(TITLE_KEY, InnovationWindowDemand.COMPANY_SITE_LENGTH);
  }


  @Override
  public String supplyInputValue(@Nonnull InnovationWindowDemandFormDto inputData) {
    return inputData.getSite();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull InnovationWindowDemand outputData) {
    outputData.setCompanySite(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
