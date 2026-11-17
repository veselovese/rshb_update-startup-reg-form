package ru.rshb.intech.digital.feature.patents.core.service.publication.notification;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;
import ru.rshb.intech.digital.references.cities.core.service.CityReferenceService;

import java.util.List;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE_TIME;

@RequiredArgsConstructor
public class InternalNotificationMsg extends NotificationMessage {

  public static final String TOPIC_KEY = "patents.showcase.publication.request";
  private static final String SUBJECT_KEY = "patents.showcase.publication.internal.email.subject";

  private final CityReferenceService cityReferenceService;
  private final PatentPublication registration;
  private final List<Attachment> attachments;

  @Override
  public String getTopic() {
    return TOPIC_KEY;
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

    APPLICANT_NAME("applicantName"),
    POSITION("position"),
    ORGANIZATION("organization"),
    PROJECT_NAME("projectName"),
    PROJECT_STAGE("projectStage"),
    PATENT_NUMBER("patentNumber"),
    PATENT_YEAR("patentYear"),
    PATENT_MPK("patentMpk"),
    PATENT_HOLDER("patentHolder"),
    INVENTION_DESCR_SHORT("inventionDescriptionShort"),
    INVENTION_DESCR_FULL("inventionDescriptionFull"),
    MANAGING_SUBJECT("managingSubject"),
    EMAIL("email"),
    PHONE("phone"),
    SITE_URL("siteUrl"),
    COMMERCIAL_IMPL("commercialImpl"),
    INVESTMENTS_AMOUNT("investmentsAmount"),
    FINANCING_ROUND("financingRound"),
    CLIENTS_AMOUNT("clientsAmount"),
    STAFF_SIZE("staffSize"),
    VICTORIES("victories"),
    CITY("city"),
    SUBMIT_DATE("submitDate"),
    SUSPICIOUS("suspicious");

    private final String key;

    @Override
    public String toString() {
      return key;
    }

    @Nonnull
    public static Values mapValues(@Nonnull PatentPublication registration, @Nonnull String city) {
      return new Values()
          .put(APPLICANT_NAME, registration.getApplicantName())
          .put(POSITION, registration.getPosition())
          .put(ORGANIZATION, registration.getOrganization())
          .put(PROJECT_NAME, registration.getProjectName())
          .put(PROJECT_STAGE, registration.getProjectStage())
          .put(PATENT_NUMBER, registration.getPatentNumber())
          .put(PATENT_YEAR, registration.getPatentYear())
          .put(PATENT_MPK, registration.getPatentMpk())
          .put(PATENT_HOLDER, registration.getPatentHolder())
          .put(INVENTION_DESCR_SHORT, registration.getInventionDescriptionShort())
          .put(INVENTION_DESCR_FULL, registration.getInventionDescriptionFull())
          .put(MANAGING_SUBJECT, registration.getManagingSubject())
          .put(EMAIL, registration.getEmail())
          .put(PHONE, registration.getPhone())
          .put(SITE_URL, registration.getSiteUrl())
          .put(COMMERCIAL_IMPL, registration.getCommercialImpl())
          .put(INVESTMENTS_AMOUNT, registration.getInvestmentsAmount())
          .put(FINANCING_ROUND, registration.getFinancingRound())
          .put(CLIENTS_AMOUNT, registration.getClientsAmount())
          .put(STAFF_SIZE, registration.getStaffSize())
          .put(VICTORIES, registration.getVictoryCases())
          .put(CITY, city)
          .put(SUBMIT_DATE, SIMPLE_DATE_TIME.format(registration.getSubmitDate()))
          .put(SUSPICIOUS, registration.getSuspicious())
          ;
    }
  }

  @Override
  public Values getValues() {
    final var city = city(registration.getCityId());
    return ReportAttributes.mapValues(registration, city);
  }

  @Nonnull
  private String city(@Nonnull String cityId) {
    return cityReferenceService.getCityWithRegionByIdOrEmpty(cityId);
  }

  @Override
  public List<Attachment> getAttachments() {
    return attachments;
  }
}
