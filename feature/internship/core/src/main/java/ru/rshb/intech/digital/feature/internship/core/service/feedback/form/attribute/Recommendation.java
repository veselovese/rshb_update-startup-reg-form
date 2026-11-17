package ru.rshb.intech.digital.feature.internship.core.service.feedback.form.attribute;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFeedbackDto;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipFeedback;
import ru.rshb.intech.digital.feature.internship.core.service.feedback.form.InternshipFeedbackForm;

/**
 * Атрибут "Рекомендовали бы вы знакомым".
 * Форма обратной связи по результатам стажировки.
 */
@Component("internshipFeedbackFormRecomendation")
@FormAttribute(InternshipFeedbackForm.class)
public class Recommendation extends TextAttributeBase<InternshipFeedbackDto, InternshipFeedback> {

  public static final String TITLE_KEY = "internship.feedback.form.attr.recommendation.to.friends";
  private static final int MAX_LENGTH = 1000;

  public Recommendation() {
    super(TITLE_KEY, true, MAX_LENGTH);
  }

  @Override
  public String supplyInputValue(InternshipFeedbackDto inputData) {
    return inputData.getRecommendation();
  }

  @Override
  public void consumeOutputValue(String outputValue, InternshipFeedback outputData) {
    outputData.setRecommendation(outputValue);
  }
}
