package ru.rshb.intech.digital.feature.patents.core.service.publication.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.patents.core.dto.PatentPublicationDto;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.service.publication.form.PatentPublicationForm;

/**
 * Атрибут "Патентообладатель".
 * Форма заявки на размещение патента в витрине патентов.
 */
@Component("patentPubFormPatentHolder")
@FormAttribute(PatentPublicationForm.class)
public class PatentHolder extends TextAttributeBase<PatentPublicationDto, PatentPublication> {

  public static final String TITLE_KEY = "patents.contest.registration.form.attribute.patent.holder";
  private static final int MAX_LENGTH = 100;

  public PatentHolder() {
    super(TITLE_KEY, MAX_LENGTH);
  }

  @Override
  public String supplyInputValue(@Nonnull PatentPublicationDto inputData) {
    return inputData.getPatentHolder();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentPublication outputData) {
    outputData.setPatentHolder(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
