package ru.rshb.intech.digital.feature.internship.core.service.feedback.form.attribute;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFeedbackDto;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipFeedback;
import ru.rshb.intech.digital.feature.internship.core.service.feedback.form.InternshipFeedbackForm;

/**
 * Атрибут "Оценка коммуникации в команде".
 * Форма обратной связи по результатам стажировки.
 */
@Component("internshipFeedbackFormTeamCommGrade")
@FormAttribute(InternshipFeedbackForm.class)
public class TeamCommGrade extends AbstractFeedbackGrade {

  public static final String TITLE_KEY = "internship.feedback.form.attr.team.comm.grade";

  public TeamCommGrade() {
    super(TITLE_KEY);
  }

  @Override
  public Integer supplyInputValue(InternshipFeedbackDto inputData) {
    return inputData.getTeamCommunicationGrade();
  }

  @Override
  public void consumeOutputValue(Integer outputValue, InternshipFeedback outputData) {
    outputData.setTeamCommunicationGrade(outputValue);
  }
}
