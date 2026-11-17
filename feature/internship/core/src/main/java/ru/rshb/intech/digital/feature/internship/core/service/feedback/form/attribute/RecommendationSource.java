package ru.rshb.intech.digital.feature.internship.core.service.feedback.form.attribute;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFeedbackDto;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipFeedback;
import ru.rshb.intech.digital.feature.internship.core.service.feedback.form.InternshipFeedbackForm;

/**
 * Атрибут "Источник рекомендации".
 * Форма обратной связи по результатам стажировки.
 */
@Component("internshipFeedbackFormRecommendSrc")
@FormAttribute(InternshipFeedbackForm.class)
public class RecommendationSource extends TextAttributeBase<InternshipFeedbackDto, InternshipFeedback> {

  public static final String TITLE_KEY = "internship.feedback.form.attr.recommendation.source";
  private static final int MAX_LENGTH = 255;

  public RecommendationSource() {
    super(TITLE_KEY, MAX_LENGTH);
  }

  @Override
  public String supplyInputValue(InternshipFeedbackDto inputData) {
    return inputData.getRecommendationSource();
  }

  @Override
  public void consumeOutputValue(String outputValue, InternshipFeedback outputData) {
    outputData.setRecommendationSource(outputValue);
  }
}
