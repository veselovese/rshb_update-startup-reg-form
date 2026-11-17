package ru.rshb.intech.digital.subsystem.contests.stipend.notification;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.Notification;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;
import ru.rshb.intech.digital.notifications.api.NotificationServiceAdapter;
import ru.rshb.intech.digital.notifications.internal.InternalNotificationTopic;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendContestQuestion;

import java.util.List;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE_TIME;

@Component
@RequiredArgsConstructor
public class StipendQuestionNotification implements NotificationServiceAdapter<StipendContestQuestion> {

  private static final String SUBJECT_TEMPLATE_KEY = "contest.stipend.question.request.email.subject";
  private static final String SUSPICIOUS_PREFIX_TEMPLATE_KEY = "common.check.status.suspicious";

  private final InternalNotificationService internalNotificationService;

  @Async
  @Override
  public void send(@Nonnull StipendContestQuestion data, @Nonnull List<Attachment> attachments) {
    internalNotificationService.send(
        new Notification(message(data, attachments))
    );
  }

  @Nonnull
  private NotificationMessage message(@Nonnull StipendContestQuestion question, @Nonnull List<Attachment> attachment) {
    return NotificationMessage.builder()
        .topic(InternalNotificationTopic.STIPEND_QUESTION_REQUEST.getKey())
        .subject(subject(question))
        .values(ReportAttributes.mapValues(question))
        .attachments(attachment)
        .build();
  }

  @Nonnull
  private String subject(@Nonnull StipendContestQuestion question) {

    final var subject = I18n.of(
        SUBJECT_TEMPLATE_KEY,
        question.getApplicantName(),
        SIMPLE_DATE_TIME.format(question.getSubmitDate())
    );

    return question.getSuspicious()
        ? I18n.of(SUSPICIOUS_PREFIX_TEMPLATE_KEY, subject)
        : subject;
  }

  @Getter
  @RequiredArgsConstructor
  private enum ReportAttributes {

    SUBMIT_DATE("submitDate"),
    APPLICANT_NAME("applicantName"),
    PHONE("phone"),
    EMAIL("email"),
    QUESTION("question"),
    SUSPICIOUS("suspicious")
    ;

    private final String key;

    @Override
    public String toString() {
      return key;
    }

    @Nonnull
    public static Values mapValues(@Nonnull StipendContestQuestion question) {
      return new Values()
          .put(SUBMIT_DATE, SIMPLE_DATE_TIME.format(question.getSubmitDate()))
          .put(APPLICANT_NAME, question.getApplicantName())
          .put(PHONE, question.getPhone())
          .put(EMAIL, question.getEmail())
          .put(QUESTION, question.getQuestion())
          .put(SUSPICIOUS, question.getSuspicious());
    }
  }
}
