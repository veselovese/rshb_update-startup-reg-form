package ru.rshb.intech.digital.feature.internship.core.service.feedback.form.attribute;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFeedbackDto;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipFeedback;
import ru.rshb.intech.digital.feature.internship.core.service.feedback.form.InternshipFeedbackForm;

/**
 * Атрибут "Уровень ожиданий".
 * Форма обратной связи по результатам стажировки.
 */
@Component("internshipFeedbackFormExpectationGrade")
@FormAttribute(InternshipFeedbackForm.class)
public class ExpectationLevelGrade extends AbstractFeedbackGrade {

  public static final String TITLE_KEY = "internship.feedback.form.attr.expect.level.grade";

  public ExpectationLevelGrade() {
    super(TITLE_KEY);
  }

  @Override
  public Integer supplyInputValue(InternshipFeedbackDto inputData) {
    return inputData.getExpectationLevelGrade();
  }

  @Override
  public void consumeOutputValue(Integer outputValue, InternshipFeedback outputData) {
    outputData.setExpectationLevelGrade(outputValue);
  }
}
