package ru.rshb.intech.digital.feature.patents.core.service.publication.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.number.IntegerAttributeBase;
import ru.rshb.intech.digital.feature.patents.core.dto.PatentPublicationDto;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.service.publication.form.PatentPublicationForm;

/**
 * Атрибут "Количество сотрудников".
 * Форма заявки на размещение патента в витрине патентов.
 */
@Component("patentPubFormStaffSize")
@FormAttribute(PatentPublicationForm.class)
public class StaffSize extends IntegerAttributeBase<PatentPublicationDto, PatentPublication> {

  public static final String TITLE_KEY = "patents.contest.registration.form.attribute.staff.size";
  public static final int MIN_VALUE = 1;
  public static final int MAX_VALUE = 999_999;

  public StaffSize() {
    super(TITLE_KEY, MIN_VALUE, MAX_VALUE, true);
  }


  @Override
  public Integer supplyInputValue(@Nonnull PatentPublicationDto inputData) {
    return inputData.getStaffSize();
  }

  @Override
  public void consumeOutputValue(Integer outputValue, @Nonnull PatentPublication outputData) {
    outputData.setStaffSize(outputValue);
  }
}
