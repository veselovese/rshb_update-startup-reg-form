package ru.rshb.intech.digital.feedback.core.service.report.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.applicant.ApplicantNameAttributeBase;
import ru.rshb.intech.digital.feedback.core.dto.FeedbackFormData;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;
import ru.rshb.intech.digital.feedback.core.service.report.form.FeedbackForm;

/**
 * Атрибут "Имя отправителя".
 * Форма обратной связи.
 */
@Component("feedbackFormReporterName")
@FormAttribute(FeedbackForm.class)
public class ReporterName extends ApplicantNameAttributeBase<FeedbackFormData, FeedbackReport> {

  public static final String TITLE_KEY = "feedback.report.form.field.reporter.name";


  public ReporterName() {
    super(TITLE_KEY, true, FeedbackReport.REPORTER_NAME_LENGTH);
  }


  @Override
  public String supplyInputValue(@Nonnull FeedbackFormData inputData) {
    return inputData.reporterName();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull FeedbackReport outputData) {
    outputData.setReporterName(outputValue);
  }
}
