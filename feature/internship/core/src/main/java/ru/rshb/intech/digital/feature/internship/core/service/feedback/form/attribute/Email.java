package ru.rshb.intech.digital.feature.internship.core.service.feedback.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.email.EmailAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFeedbackDto;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipFeedback;
import ru.rshb.intech.digital.feature.internship.core.exception.FeedbackValidationException;
import ru.rshb.intech.digital.feature.internship.core.service.feedback.form.InternshipFeedbackForm;
import ru.rshb.intech.digital.feature.internship.core.service.feedback.form.InternshipFeedbackService;

/**
 * Атрибут "Электронная почта".
 * Форма обратной связи по результатам стажировки.
 */
@Component("internshipFeedbackFormEmail")
@FormAttribute(InternshipFeedbackForm.class)
public class Email extends EmailAttributeBase<InternshipFeedbackDto, InternshipFeedback> {

  public Email(@Nonnull InternshipFeedbackService feedbackService) {
    super(new UniqueEmailValidator(feedbackService));
  }

  @Override
  public String supplyInputValue(InternshipFeedbackDto inputData) {
    return inputData.getEmail();
  }

  @Override
  public void consumeOutputValue(String outputValue, InternshipFeedback outputData) {
    outputData.setEmail(outputValue);
  }

  @RequiredArgsConstructor
  private static class UniqueEmailValidator
      implements BusinessRulesValidator<InternshipFeedbackDto, InternshipFeedback, String> {

    private final InternshipFeedbackService feedbackService;

    @Override
    public void validate(String email, @Nonnull FormContext<InternshipFeedbackDto, InternshipFeedback> context)
        throws AttributeBusinessRulesException {

      if (feedbackService.isFeedbackExist(email)) {
        throw FeedbackValidationException.feedbackReportNumberExceeded();
      }
    }
  }
}
