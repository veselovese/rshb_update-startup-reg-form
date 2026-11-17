package ru.rshb.intech.digital.feedback.core.service.report.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.email.EmailAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feedback.core.FeedbackOptions;
import ru.rshb.intech.digital.feedback.core.dto.FeedbackFormData;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;
import ru.rshb.intech.digital.feedback.core.exception.FeedbackException;
import ru.rshb.intech.digital.feedback.core.service.FeedbackService;
import ru.rshb.intech.digital.feedback.core.service.report.form.FeedbackForm;

/**
 * Атрибут "Электронная почта".
 * Форма обратной связи.
 */
@Component("feedbackFormEmail")
@FormAttribute(FeedbackForm.class)
public class Email extends EmailAttributeBase<FeedbackFormData, FeedbackReport> {


  public Email(@Nonnull FeedbackService feedbackService,
               @Nonnull FeedbackOptions feedbackOptions) {

    super(new DuplicateEmailValidator(feedbackOptions, feedbackService));
  }


  @Override
  public String supplyInputValue(@Nonnull FeedbackFormData inputData) {
    return inputData.email();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull FeedbackReport outputData) {
    outputData.setEmail(outputValue);
  }


  @RequiredArgsConstructor
  private static class DuplicateEmailValidator
      implements BusinessRulesValidator<FeedbackFormData, FeedbackReport, String> {

    private final FeedbackOptions feedbackOptions;
    private final FeedbackService feedbackService;

    @Override
    public void validate(String email,
                         @Nonnull FormContext<FeedbackFormData, FeedbackReport> context)
        throws AttributeBusinessRulesException {

      final var existingReportsNum = feedbackService.getReportsNumberByEmail(email);
      assertMaxReportsNumberNotExceeded(existingReportsNum);
    }

    private void assertMaxReportsNumberNotExceeded(long reportsNumber) {

      if (reportsNumber >= feedbackOptions.maxReportsCountPerInterval()) {
        throw FeedbackException.requestLimitException(feedbackOptions.intervalHours());
      }
    }
  }
}
