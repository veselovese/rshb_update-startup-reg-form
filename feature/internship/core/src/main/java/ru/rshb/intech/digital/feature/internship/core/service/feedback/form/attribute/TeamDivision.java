package ru.rshb.intech.digital.feature.internship.core.service.feedback.form.attribute;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFeedbackDto;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipFeedback;
import ru.rshb.intech.digital.feature.internship.core.service.feedback.form.InternshipFeedbackForm;

/**
 * Атрибут "Команда и подразделение".
 * Форма обратной связи по результатам стажировки.
 */
@Component("internshipFeedbackFormTeamAndDivision")
@FormAttribute(InternshipFeedbackForm.class)
public class TeamDivision extends TextAttributeBase<InternshipFeedbackDto, InternshipFeedback> {

  public static final String TITLE_KEY = "internship.feedback.form.attr.team.division";
  private static final int MAX_LENGTH = 255;

  public TeamDivision() {
    super(TITLE_KEY, MAX_LENGTH);
  }

  @Override
  public String supplyInputValue(InternshipFeedbackDto inputData) {
    return inputData.getTeamAndDivision();
  }

  @Override
  public void consumeOutputValue(String outputValue, InternshipFeedback outputData) {
    outputData.setTeamDivision(outputValue);
  }
}
