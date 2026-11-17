package ru.rshb.intech.digital.feature.innovations.core.service.general.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.applicant.ApplicantNameAttributeBase;
import ru.rshb.intech.digital.feature.innovations.core.dto.GeneralRequestDto;
import ru.rshb.intech.digital.feature.innovations.core.entity.GeneralRequest;
import ru.rshb.intech.digital.feature.innovations.core.service.general.form.GeneralRequestForm;

/**
 * Атрибут "ФИО".
 * Форма заявки с предложением или запросом инноваций.
 */
@Component("innovationsGeneralRequestFormApplicantName")
@FormAttribute(GeneralRequestForm.class)
public class ApplicantName extends ApplicantNameAttributeBase<GeneralRequestDto, GeneralRequest> {

  public ApplicantName() {
    super(GeneralRequest.APPLICANT_NAME_LENGTH);
  }


  @Override
  public String supplyInputValue(@Nonnull GeneralRequestDto inputData) {
    return inputData.getApplicantName();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull GeneralRequest outputData) {
    outputData.setApplicantName(outputValue);
  }
}
