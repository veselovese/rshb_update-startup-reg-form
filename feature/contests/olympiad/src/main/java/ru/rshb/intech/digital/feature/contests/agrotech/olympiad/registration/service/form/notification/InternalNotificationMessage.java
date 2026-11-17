package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.notification;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadRegistration;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;

import java.util.Collections;
import java.util.List;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE_TIME;

@RequiredArgsConstructor
public class InternalNotificationMessage extends NotificationMessage {

  private static final String TOPIC_ID = "contest.agrotech.olympiad.registration.request";
  private static final String SUBJECT_TEMPLATE_KEY = "agrotech.olympiad.registration.internal.email.subject";

  private final AgrotechOlympiadRegistration registration;

  @Override
  public String getTopic() {
    return TOPIC_ID;
  }

  @Override
  public String getSubject() {
    return I18n.of(
        SUBJECT_TEMPLATE_KEY,
        registration.getApplicantName(),
        SIMPLE_DATE_TIME.format(registration.getSubmitDate())
    );
  }

  @Override
  public boolean isUseDefaultSubject() {
    return false;
  }

  @Getter
  @RequiredArgsConstructor
  private enum ReportAttributes {

    SUBMIT_DATE("submitDate"),
    APPLICANT_NAME("applicantName"),
    EMAIL("email"),
    PHONE("phone"),
    VK_URL("vkProfileUrl"),
    DIRECTIONS("directions"),
    UNIVERSITY("university")
    ;

    private final String key;

    @Override
    public String toString() {
      return key;
    }

    @Nonnull
    public static Values mapValues(@Nonnull AgrotechOlympiadRegistration registration) {
      return new Values()
          .put(SUBMIT_DATE, SIMPLE_DATE_TIME.format(registration.getSubmitDate()))
          .put(APPLICANT_NAME, registration.getApplicantName())
          .put(EMAIL, registration.getEmail())
          .put(PHONE, registration.getPhone())
          .put(VK_URL, registration.getVkProfileUrl())
          .put(DIRECTIONS, registration.getDirections())
          .put(UNIVERSITY, registration.getUniversity().getShortTitle());
    }
  }

  @Override
  public Values getValues() {
    return ReportAttributes.mapValues(registration);
  }

  @Override
  public List<Attachment> getAttachments() {
    return Collections.emptyList();
  }
}
