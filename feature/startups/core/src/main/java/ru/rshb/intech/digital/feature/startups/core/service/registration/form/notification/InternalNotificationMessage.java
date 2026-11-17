package ru.rshb.intech.digital.feature.startups.core.service.registration.form.notification;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.startups.entity.StartupCategory;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;

import java.util.List;
import java.util.Optional;

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
    POSITION("position"),
    PHONE("phone"),
    EMAIL("email"),
    VK_URL("vkProfileUrl"),
    PROJECT_NAME("projectName"),
    COUNTRY("country"),
    CITY("city"),
    PROJECT_DESCR_SHORT("projectDescriptionShort"),
    PROJECT_DESCR_FULL("projectDescriptionFull"),
    INVESTMENTS_VALUE("investValue"),
    INVESTMENTS_CURRENCY("investCurr"),
    CATEGORY("category"),
    FOUNDED_YEAR("foundedYear"),
    STAFF_SIZE("staffSize"),
    PROJECT_STAGE("projectStage"),
    CLIENTS_NUMBER("clientsNumber"),
    FINANCING_ROUND("finRound")
    ;

    private final String key;

    @Override
    public String toString() {
      return key;
    }

    @Nonnull
    public static Values mapValues(@Nonnull StartupRegistration registration, @Nonnull String category) {
      return new Values()
          .put(SUSPICIOUS, registration.getSuspicious())
          .put(SUBMIT_DATE, SIMPLE_DATE_TIME.format(registration.getSubmitDate()))
          .put(APPLICANT_NAME, registration.getApplicantName())
          .put(ORGANIZATION, registration.getOrganization())
          .put(POSITION, registration.getPosition())
          .put(PHONE, registration.getPhone())
          .put(EMAIL, registration.getEmail())
          .put(VK_URL, registration.getVkProfileUrl())
          .put(PROJECT_NAME, registration.getProjectTitle())
          .put(COUNTRY, registration.getCountry().getTitle())
          .put(CITY, registration.getCity().getTitle())
          .put(PROJECT_DESCR_SHORT, registration.getProjectDescriptionShort())
          .put(PROJECT_DESCR_FULL, registration.getProjectDescriptionFull())
          .put(INVESTMENTS_VALUE, registration.getInvestmentsValue())
          .put(INVESTMENTS_CURRENCY, registration.getInvestmentsCurrency().getGrapheme())
          .put(CATEGORY, category)
          .put(FOUNDED_YEAR, registration.getFoundationYear())
          .put(STAFF_SIZE, registration.getStaffSize())
          .put(PROJECT_STAGE, registration.getProjectStage().getTitle())
          .put(CLIENTS_NUMBER, registration.getClientsNumberVariant().getTitle())
          .put(FINANCING_ROUND, registration.getFinancingRound().getTitle());
    }
  }

  @Override
  public Values getValues() {
    return ReportAttributes.mapValues(registration, category());
  }

  private String category() {
    return Optional.ofNullable(registration.getCategory())
        .map(StartupCategory::getTitle)
        .orElseGet(registration::getCategoryCustom);
  }

  @Override
  public List<Attachment> getAttachments() {
    return attachments;
  }
}
