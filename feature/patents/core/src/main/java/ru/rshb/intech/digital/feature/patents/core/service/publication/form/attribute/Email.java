package ru.rshb.intech.digital.feature.patents.core.service.publication.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.email.EmailAttributeBase;
import ru.rshb.intech.digital.feature.patents.core.dto.PatentPublicationDto;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.service.publication.form.PatentPublicationForm;

/**
 * Атрибут "Электронная почта".
 * Форма заявки на размещение патента в витрине патентов.
 */
@Component("patentPubFormEmail")
@FormAttribute(PatentPublicationForm.class)
public class Email extends EmailAttributeBase<PatentPublicationDto, PatentPublication> {


  @Override
  public String supplyInputValue(@Nonnull PatentPublicationDto inputData) {
    return inputData.getEmail();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentPublication outputData) {
    outputData.setEmail(outputValue);
  }
}
