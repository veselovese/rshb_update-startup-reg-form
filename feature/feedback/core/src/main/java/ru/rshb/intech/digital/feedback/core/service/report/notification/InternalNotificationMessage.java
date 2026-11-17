package ru.rshb.intech.digital.feedback.core.service.report.notification;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feedback.core.entity.FeedbackReport;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;

import java.util.List;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE_TIME;
import static ru.rshb.intech.digital.notifications.internal.InternalNotificationTopic.FEEDBACK_REPORT;

@RequiredArgsConstructor
public class InternalNotificationMessage extends NotificationMessage {

  private static final String SUBJECT_TEMPLATE_KEY = "feedback.notification.email.subject";
  private static final String SUSPICIOUS_PREFIX_KEY = "common.check.status.suspicious";

  private final FeedbackReport report;
  private final List<Attachment> attachments;

  @Override
  public String getTopic() {
    return FEEDBACK_REPORT.getKey();
  }

  @Override
  public String getSubject() {

    final var reporter = StringUtils.defaultString(report.getReporterName());
    final var date = SIMPLE_DATE_TIME.format(report.getSubmitDate());
    final var subject = I18n.of(SUBJECT_TEMPLATE_KEY, reporter, date);

    return report.isSuspicious()
        ? I18n.of(SUSPICIOUS_PREFIX_KEY, subject)
        : subject;
  }

  @Override
  public boolean isUseDefaultSubject() {
    return false;
  }

  @Getter
  @RequiredArgsConstructor
  private enum ReportAttributes {

    REPORTER_NAME("reporterName"),
    PHONE("phone"),
    EMAIL("email"),
    FEEDBACK("feedback"),
    SUBMIT_DATE("submitDate"),
    SUSPICIOUS("suspicious");

    private final String key;

    @Override
    public String toString() {
      return key;
    }

    @Nonnull
    public static Values mapValues(@Nonnull FeedbackReport report) {
      return new Values()
          .put(REPORTER_NAME, report.getReporterName())
          .put(PHONE, report.getPhone())
          .put(EMAIL, report.getEmail())
          .put(FEEDBACK, report.getFeedback())
          .put(SUBMIT_DATE, SIMPLE_DATE_TIME.format(report.getSubmitDate()))
          .put(SUSPICIOUS, report.isSuspicious());
    }
  }

  @Override
  public Values getValues() {
    return ReportAttributes.mapValues(report);
  }

  @Override
  public List<Attachment> getAttachments() {
    return attachments;
  }
}
