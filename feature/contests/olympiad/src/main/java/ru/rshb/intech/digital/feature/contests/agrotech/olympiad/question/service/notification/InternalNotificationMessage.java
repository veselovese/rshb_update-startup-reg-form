package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.service.notification;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadQuestion;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;

import java.util.Collections;
import java.util.List;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE_TIME;

@RequiredArgsConstructor
public class InternalNotificationMessage extends NotificationMessage {

  private static final String TOPIC_ID = "contest.agrotech.olympiad.question.request";
  private static final String SUBJECT_TEMPLATE_KEY = "agrotech.olympiad.question.internal.email.subject";

  private final AgrotechOlympiadQuestion question;

  @Override
  public String getTopic() {
    return TOPIC_ID;
  }

  @Override
  public String getSubject() {
    final var submitDate = SIMPLE_DATE_TIME.format(question.getSubmitDate());
    return I18n.of(SUBJECT_TEMPLATE_KEY,  question.getApplicantName(), submitDate);
  }

  @Override
  public boolean isUseDefaultSubject() {
    return false;
  }

  @Getter
  @RequiredArgsConstructor
  private enum ReportAttributes {

    APPLICANT_NAME("applicantName"),
    EMAIL("email"),
    PHONE("phone"),
    QUESTION("question"),
    SUBMIT_DATE("submitDate");

    private final String key;

    @Override
    public String toString() {
      return key;
    }

    @Nonnull
    public static Values mapValues(@Nonnull AgrotechOlympiadQuestion question) {
      return new Values()
          .put(APPLICANT_NAME, question.getApplicantName())
          .put(EMAIL, question.getEmail())
          .put(PHONE, question.getPhone())
          .put(QUESTION, question.getQuestion())
          .put(SUBMIT_DATE, SIMPLE_DATE_TIME.format(question.getSubmitDate()));
    }
  }

  @Override
  public Values getValues() {
    return ReportAttributes.mapValues(question);
  }

  @Override
  public List<Attachment> getAttachments() {
    return Collections.emptyList();
  }
}
