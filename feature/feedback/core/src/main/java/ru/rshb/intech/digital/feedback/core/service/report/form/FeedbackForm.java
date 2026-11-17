package ru.rshb.intech.digital.feedback.core.service.report.form;

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
import ru.rshb.intech.digital.feedback.core.dto.FeedbackFormData;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;
import ru.rshb.intech.digital.feedback.core.service.FeedbackService;
import ru.rshb.intech.digital.feedback.core.service.report.notification.FeedbackReportNotification;

import java.util.List;

/**
 * Форма обратной связи.
 */
@Component
public class FeedbackForm extends Form<FeedbackFormData, FeedbackReport> {

  public static final String TITLE_KEY = "feedback.report.form.name";

  private final FeedbackService feedbackService;
  private final FeedbackReportNotification feedbackReportNotification;

  public FeedbackForm(@Nonnull FormAttributeProvider attributeProvider,
                      @Nonnull CensorAnalyzer censorAnalyzer,
                      @Nonnull FeedbackService feedbackService,
                      @Nonnull FeedbackReportNotification feedbackReportNotification) {

    super(attributeProvider, TITLE_KEY, new CensorPreCheck<>(censorAnalyzer));

    this.feedbackService = feedbackService;
    this.feedbackReportNotification = feedbackReportNotification;
  }

  @Nonnull
  @Override
  public FeedbackReport process(@Nonnull FeedbackFormData feedbackDto) {

    final var attachments = Attachments.list()
        .ofNullable(feedbackDto.attachment(), feedbackService.getFormAttachmentAllowedExtensions())
        .collectNonNull();

    return process(
        feedbackDto,
        FeedbackReport::new,
        new ResultHandler(feedbackService, feedbackReportNotification, attachments)
    );
  }

  @RequiredArgsConstructor
  private static class ResultHandler implements FormResultHandler<FeedbackFormData, FeedbackReport> {

    private final FeedbackService feedbackService;
    private final FeedbackReportNotification reportNotification;
    private final List<Attachment> attachments;

    @Nonnull
    @Override
    public FeedbackReport handleResult(@Nonnull FeedbackReport feedback,
                                       @Nonnull FormContext<FeedbackFormData, FeedbackReport> context) {

      if (context.is(PreCheckResult.SUSPICIOUS)) {
        feedback.setSuspicious(true);
      }

      final var savedReport = feedbackService.save(feedback);

      reportNotification.send(savedReport, attachments);

      return savedReport;
    }
  }
}
