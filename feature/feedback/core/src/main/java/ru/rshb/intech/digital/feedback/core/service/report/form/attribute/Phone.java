package ru.rshb.intech.digital.feedback.core.service.report.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.phone.PhoneAttributeBase;
import ru.rshb.intech.digital.feedback.core.dto.FeedbackFormData;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;
import ru.rshb.intech.digital.feedback.core.service.report.form.FeedbackForm;

/**
 * Атрибут "Номер телефона".
 * Форма обратной связи.
 */
@Component("feedbackFormPhone")
@FormAttribute(FeedbackForm.class)
public class Phone extends PhoneAttributeBase<FeedbackFormData, FeedbackReport> {

  public Phone() {
    super(true);
  }

  @Override
  public String supplyInputValue(@Nonnull FeedbackFormData inputData) {
    return inputData.phone();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull FeedbackReport outputData) {
    outputData.setPhone(outputValue);
  }
}
