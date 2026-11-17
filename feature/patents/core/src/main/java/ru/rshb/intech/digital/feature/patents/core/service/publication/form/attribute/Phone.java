package ru.rshb.intech.digital.feature.patents.core.service.publication.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.phone.PhoneAttributeBase;
import ru.rshb.intech.digital.feature.patents.core.dto.PatentPublicationDto;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.service.publication.form.PatentPublicationForm;

/**
 * Атрибут "Номер телефона".
 * Форма заявки на размещение патента в витрине патентов.
 */
@Component("patentPubFormPhone")
@FormAttribute(PatentPublicationForm.class)
public class Phone extends PhoneAttributeBase<PatentPublicationDto, PatentPublication> {

  @Override
  public String supplyInputValue(@Nonnull PatentPublicationDto inputData) {
    return inputData.getPhone();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentPublication outputData) {
    outputData.setPhone(outputValue);
  }
}
