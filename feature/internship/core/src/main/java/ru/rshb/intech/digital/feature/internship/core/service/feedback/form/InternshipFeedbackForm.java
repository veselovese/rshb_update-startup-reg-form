package ru.rshb.intech.digital.feature.internship.core.service.feedback.form;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.censor.forms.CensorAnalyzer;
import ru.rshb.intech.digital.censor.forms.CensorPreCheck;
import ru.rshb.intech.digital.commons.forms.Form;
import ru.rshb.intech.digital.commons.forms.FormResultHandler;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckResult;
import ru.rshb.intech.digital.commons.forms.attributes.provider.FormAttributeProvider;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.commons.utils.attachment.Attachments;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFeedbackDto;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipFeedback;
import ru.rshb.intech.digital.feature.internship.core.service.feedback.form.notification.InternshipFeedbackNotification;

import java.util.List;

/**
 * Форма обратной связи по результатам стажировки.
 */
@Component
public class InternshipFeedbackForm extends Form<InternshipFeedbackDto, InternshipFeedback> {

  public static final String TITLE_KEY = "internship.feedback.form.name";

  private final InternshipFeedbackService feedbackService;
  private final InternshipFeedbackNotification notificationService;


  public InternshipFeedbackForm(@Nonnull FormAttributeProvider attributeProvider,
                                @Nonnull CensorAnalyzer censorAnalyzer,
                                @Nonnull InternshipFeedbackService feedbackService,
                                @Nonnull InternshipFeedbackNotification notificationService) {
    super(attributeProvider, TITLE_KEY, new CensorPreCheck<>(censorAnalyzer));

    this.feedbackService = feedbackService;
    this.notificationService = notificationService;
  }

  @Nonnull
  @Override
  public InternshipFeedback process(@Nonnull InternshipFeedbackDto inputData) {

    final var attachments = Attachments.list()
        .ofNullable(inputData.getAttachmentPhoto(), feedbackService.getFormAttachmentAllowedExtensions())
        .collectNonNull();

    return process(
        inputData,
        InternshipFeedback::new,
        new ResultHandler(feedbackService, notificationService, attachments)
    );
  }

  @RequiredArgsConstructor
  private static class ResultHandler implements FormResultHandler<InternshipFeedbackDto, InternshipFeedback> {

    private final InternshipFeedbackService feedbackService;
    private final InternshipFeedbackNotification notificationService;
    private final List<Attachment> attachments;

    @Nonnull
    @Override
    public InternshipFeedback handleResult(@Nonnull InternshipFeedback result,
                                           @Nonnull FormContext<InternshipFeedbackDto, InternshipFeedback> context) {

      if (context.is(PreCheckResult.SUSPICIOUS)) {
        result.setSuspicious(true);
      }

      final var savedFeedback = feedbackService.save(result);

      notificationService.send(savedFeedback, attachments);

      return savedFeedback;
    }
  }
}
