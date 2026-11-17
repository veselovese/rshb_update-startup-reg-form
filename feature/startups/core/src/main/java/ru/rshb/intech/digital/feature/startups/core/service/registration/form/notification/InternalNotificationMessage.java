package ru.rshb.intech.digital.feature.startups.core.service.registration.form.notification;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;

import java.util.List;
import java.util.stream.Collectors;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE_TIME;

@RequiredArgsConstructor
public class InternalNotificationMessage extends NotificationMessage {

  private static final String TOPIC_ID = "startups.registration.request";
  private static final String SUBJECT_KEY = "startups.registration.internal.email.subject";

  private final StartupRegistration registration;
  private final List<Attachment> attachments;

  @Override
  public String getTopic() {
    return TOPIC_ID;
  }

  @Override
  public String getSubject() {
    return I18n.of(
        SUBJECT_KEY,
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

    SUSPICIOUS("suspicious"),
    SUBMIT_DATE("submitDate"),
    APPLICANT_NAME("applicantName"),
    ORGANIZATION("organization"),
    PHONE("phone"),
    EMAIL("email"),
    PROJECT_NAME("projectName"),
    CITY("city"),
    PROJECT_DESCR_SHORT("projectDescriptionShort"),
    PROJECT_DESCR_FULL("projectDescriptionFull"),
    INVESTMENTS_VALUE("investValue"),
    INN("inn"),
    WEBSITE("websiteUrl"),
    INVESTOR("investorName"),
    FOUNDED_YEAR("foundedYear"),
    PROJECT_STAGE("projectStage"),
    REVENUE_RANGE("revenueRange"),
    CATEGORIES("categories"),
    AI_SOLUTIONS("hasAiSolutions"),
    VIDEO("videoUrl")
    ;

    private final String key;

    @Override
    public String toString() {
      return key;
    }

    @Nonnull
    public static Values mapValues(@Nonnull StartupRegistration registration) {
      return new Values()
          .put(SUSPICIOUS, registration.getSuspicious())
          .put(SUBMIT_DATE, SIMPLE_DATE_TIME.format(registration.getSubmitDate()))
          .put(APPLICANT_NAME, registration.getApplicantName())
          .put(ORGANIZATION, registration.getOrganization())
          .put(PHONE, registration.getPhone())
          .put(EMAIL, registration.getEmail())
          .put(PROJECT_NAME, registration.getProjectTitle())
          .put(CITY, registration.getCity())
          .put(PROJECT_DESCR_SHORT, registration.getProjectDescriptionShort())
          .put(PROJECT_DESCR_FULL, registration.getProjectDescriptionFull())
          .put(INVESTMENTS_VALUE, registration.getInvestmentsValue())
          .put(INN, registration.getInn())
          .put(WEBSITE, registration.getWebsiteUrl())
          .put(INVESTOR, registration.getInvestorName())
          .put(FOUNDED_YEAR, registration.getFoundationYear())
          .put(PROJECT_STAGE, registration.getProjectStage().getTitle())
          .put(REVENUE_RANGE, registration.getRevenueRange())
          .put(CATEGORIES, registration.getCategories().stream()
              .map(category -> category.getTitle())
              .collect(Collectors.joining(", ")))
          .put(AI_SOLUTIONS, Boolean.TRUE.equals(registration.getHasAiSolutions()) ? "Есть" : "Нет")
          .put(VIDEO, registration.getVideoUrl());
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
