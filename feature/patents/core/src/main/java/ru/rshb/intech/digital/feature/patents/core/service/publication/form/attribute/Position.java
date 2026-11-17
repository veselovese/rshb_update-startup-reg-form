package ru.rshb.intech.digital.feature.patents.core.service.publication.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.patents.core.dto.PatentPublicationDto;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.service.publication.form.PatentPublicationForm;

/**
 * Атрибут "Должность".
 * Форма заявки на размещение патента в витрине патентов.
 */
@Component("patentPubFormPosition")
@FormAttribute(PatentPublicationForm.class)
public class Position extends TextAttributeBase<PatentPublicationDto, PatentPublication> {

  public static final String TITLE_KEY = "common.form.attribute.position.title";
  private static final int MAX_LENGTH = 100;

  public Position() {
    super(TITLE_KEY, true, MAX_LENGTH);
  }

  @Override
  public String supplyInputValue(@Nonnull PatentPublicationDto inputData) {
    return inputData.getPosition();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentPublication outputData) {
    outputData.setPosition(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
