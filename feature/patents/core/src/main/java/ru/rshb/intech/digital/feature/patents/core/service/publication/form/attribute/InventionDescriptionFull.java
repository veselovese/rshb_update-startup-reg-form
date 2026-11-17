package ru.rshb.intech.digital.feature.patents.core.service.publication.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.patents.core.dto.PatentPublicationDto;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.service.publication.form.PatentPublicationForm;

/**
 * Атрибут "Описание изобретения".
 * Форма заявки на размещение патента в витрине патентов.
 */
@Component("patentPubFormInventionDescriptionFull")
@FormAttribute(PatentPublicationForm.class)
public class InventionDescriptionFull
    extends TextAttributeBase<PatentPublicationDto, PatentPublication> {

  public static final String TITLE_KEY = "patents.contest.registration.form.attribute.project.description.full";
  private static final int MAX_LENGTH = 3000;

  public InventionDescriptionFull() {
    super(TITLE_KEY, MAX_LENGTH);
  }

  @Override
  public String supplyInputValue(@Nonnull PatentPublicationDto inputData) {
    return inputData.getInvention();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentPublication outputData) {
    outputData.setInventionDescriptionFull(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
