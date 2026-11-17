package ru.rshb.intech.digital.feedback.core.service.report.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feedback.core.dto.FeedbackFormData;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;
import ru.rshb.intech.digital.feedback.core.service.report.form.FeedbackForm;

/**
 * Атрибут "Текст отзыва".
 * Форма обратной связи.
 */
@Component("feedbackReportText")
@FormAttribute(FeedbackForm.class)
public class Feedback extends TextAttributeBase<FeedbackFormData, FeedbackReport> {

  public static final String TITLE_KEY = "feedback.report.form.field.description";

  public Feedback() {
    super(TITLE_KEY, false, FeedbackReport.FEEDBACK_LENGTH);
  }

  @Override
  public String supplyInputValue(@Nonnull FeedbackFormData inputData) {
    return inputData.feedback();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull FeedbackReport outputData) {
    outputData.setFeedback(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
