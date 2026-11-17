package ru.rshb.intech.digital.feature.internship.core.service.feedback.form.attribute;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.phone.PhoneAttributeBase;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFeedbackDto;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipFeedback;
import ru.rshb.intech.digital.feature.internship.core.service.feedback.form.InternshipFeedbackForm;

/**
 * Атрибут "Номер телефона".
 * Форма обратной связи по результатам стажировки.
 */
@Component("internshipFeedbackFormPhone")
@FormAttribute(InternshipFeedbackForm.class)
public class Phone extends PhoneAttributeBase<InternshipFeedbackDto, InternshipFeedback> {

  @Override
  public String supplyInputValue(InternshipFeedbackDto inputData) {
    return inputData.getPhone();
  }

  @Override
  public void consumeOutputValue(String outputValue, InternshipFeedback outputData) {
    outputData.setPhone(outputValue);
  }
}
