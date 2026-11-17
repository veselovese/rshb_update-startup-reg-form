package ru.rshb.intech.digital.vacancies.core.service.candidate.notification;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;
import ru.rshb.intech.digital.vacancies.entity.CandidateCv;

import java.util.List;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE_TIME;

@RequiredArgsConstructor
public class InternalNotificationMessage extends NotificationMessage {

  private static final String TOPIC_ID = "vacancies.candidate.cv.request";
  private static final String SUBJECT_KEY = "vacancies.candidate.cv.email.subject";

  private final CandidateCv candidateCv;
  private final List<Attachment> attachments;

  @Override
  public String getTopic() {
    return TOPIC_ID;
  }

  @Override
  public String getSubject() {
    return I18n.of(
        SUBJECT_KEY,
        candidateCv.getCandidateName(),
        SIMPLE_DATE_TIME.format(candidateCv.getSubmitDate())
    );
  }

  @Override
  public boolean isUseDefaultSubject() {
    return false;
  }

  @Getter
  @RequiredArgsConstructor
  private enum ReportAttributes {

    SUSPICIOUS("suspicious"),
    SUMBIT_DATE("submitDate"),
    FULL_NAME("fullName"),
    PHONE("phone"),
    EMAIL("email"),
    COV_LETTER("covLetter");

    private final String key;

    @Override
    public String toString() {
      return key;
    }

    @Nonnull
    public static Values mapValues(@Nonnull CandidateCv candidateCv) {
      return new Values()
          .put(SUSPICIOUS, candidateCv.getSuspicious())
          .put(SUMBIT_DATE, SIMPLE_DATE_TIME.format(candidateCv.getSubmitDate()))
          .put(FULL_NAME, candidateCv.getCandidateName())
          .put(PHONE, candidateCv.getPhone())
          .put(EMAIL, candidateCv.getEmail())
          .put(COV_LETTER, candidateCv.getCoveringLetter());
    }
  }

  @Override
  public Values getValues() {
    return ReportAttributes.mapValues(candidateCv);
  }

  @Override
  public List<Attachment> getAttachments() {
    return attachments;
  }
}
