package ru.rshb.intech.digital.feature.internship.core.service.feedback.form.attribute;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFeedbackDto;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipFeedback;
import ru.rshb.intech.digital.feature.internship.core.service.feedback.form.InternshipFeedbackForm;

/**
 * Атрибут "ФИО стажера".
 * Форма обратной связи по результатам стажировки.
 */
@Component("internshipFeedbackFormTraineeName")
@FormAttribute(InternshipFeedbackForm.class)
public class TraineeName extends TextAttributeBase<InternshipFeedbackDto, InternshipFeedback> {

  public static final String TITLE_KEY = "internship.feedback.form.attr.trainee.name";
  private static final int MAX_LENGTH = 255;

  public TraineeName() {
    super(TITLE_KEY, MAX_LENGTH);
  }

  @Override
  public String supplyInputValue(InternshipFeedbackDto inputData) {
    return inputData.getTraineeName();
  }

  @Override
  public void consumeOutputValue(String outputValue, InternshipFeedback outputData) {
    outputData.setTraineeName(outputValue);
  }
}
