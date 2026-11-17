package ru.rshb.intech.digital.feature.patents.core.service.publication.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.patents.core.dto.PatentPublicationDto;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.service.publication.form.PatentPublicationForm;

/**
 * Атрибут "Организация".
 * Форма заявки на размещение патента в витрине патентов.
 */
@Component("patentPubFormOrganization")
@FormAttribute(PatentPublicationForm.class)
public class Organization extends TextAttributeBase<PatentPublicationDto, PatentPublication> {

  public static final String TITLE_KEY = "common.form.attribute.org.title";
  private static final int MAX_LENGTH = 100;

  public Organization() {
    super(TITLE_KEY, true, MAX_LENGTH);
  }

  @Override
  public String supplyInputValue(@Nonnull PatentPublicationDto inputData) {
    return inputData.getOrganization();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentPublication outputData) {
    outputData.setOrganization(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
