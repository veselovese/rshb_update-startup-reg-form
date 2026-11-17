package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.notification;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;

import java.util.List;

import static ru.rshb.intech.digital.commons.utils.StringUtilz.splitOrEmpty;
import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE_TIME;
import static ru.rshb.intech.digital.notifications.internal.InternalNotificationTopic.BLOGS_EXPERT_REGISTRATION_REQUEST;

@RequiredArgsConstructor
class InternalNotificationMessage extends NotificationMessage {

  private static final String SUBJECT_TEMPLATE_KEY = "expert.registration.email.subject";
  private static final String SUSPICIOUS_PREFIX_TEMPLATE_KEY = "common.check.status.suspicious";

  private final ExpertRegistration registration;
  private final List<Attachment> attachments;


  @Override
  public String getTopic() {
    return BLOGS_EXPERT_REGISTRATION_REQUEST.getKey();
  }

  @Override
  public String getSubject() {

    final var applicantName = registration.getExpertName();
    final var submitDate = SIMPLE_DATE_TIME.format(registration.getSubmitDate());

    final var subject = I18n.of(SUBJECT_TEMPLATE_KEY, applicantName, submitDate);

    return registration.getSuspicious()
        ? I18n.of(SUSPICIOUS_PREFIX_TEMPLATE_KEY, subject)
        : subject;
  }

  @Override
  public boolean isUseDefaultSubject() {
    return false;
  }

  @Getter
  @RequiredArgsConstructor
  private enum ReportAttributes {

    EXPERT_NAME("expertName"),
    EMAIL("email"),
    PHONE("phone"),
    VK_PROFILE_URL("vkProfileUrl"),
    SUBMIT_DATE("registrationDate"),
    COMMENT("comment"),
    DIRECTIONS("directions"),
    PERFORMANCE_URLS("performanceUrls"),
    SUSPICIOUS("suspicious");

    private final String key;

    @Override
    public String toString() {
      return key;
    }

    @Nonnull
    public static Values mapValues(@Nonnull ExpertRegistration registration) {
      return new Values()
          .put(EXPERT_NAME, registration.getExpertName())
          .put(EMAIL, registration.getEmail())
          .put(PHONE, registration.getPhone())
          .put(VK_PROFILE_URL, registration.getVkUrl())
          .put(SUBMIT_DATE, SIMPLE_DATE_TIME.format(registration.getSubmitDate()))
          .put(COMMENT, registration.getComment())
          .put(DIRECTIONS, splitOrEmpty(registration.getDirections()))
          .put(PERFORMANCE_URLS, splitOrEmpty(registration.getPerformanceUrls()))
          .put(SUSPICIOUS, registration.getSuspicious());
    }
  }

  @Override
  public Values getValues() {
    return ReportAttributes.mapValues(registration);
  }



  @Override
  public List<Attachment> getAttachments() {
    return attachments;
  }
}
