package ru.rshb.intech.digital.feature.internship.core.service.notification;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.repository.registration.RegistrationRepository;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.Notification;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;
import ru.rshb.intech.digital.references.cities.core.service.CityReferenceService;
import ru.rshb.intech.digital.references.universities.core.service.UniversityReferenceService;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE;
import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE_TIME;
import static ru.rshb.intech.digital.notifications.internal.InternalNotificationTopic.INTERNSHIP_REGISTRATION_REQUEST;

@Component
@RequiredArgsConstructor
public class NewAppNotificationMessage {

  private static final String SUBJECT_TEMPLATE_KEY = "internship.registration.email.subject";
  private static final String SUSPICIOUS_PREFIX_KEY = "common.check.status.suspicious";

  private final InternalNotificationService internalNotificationService;
  private final CityReferenceService cityService;
  private final UniversityReferenceService universityService;
  private final RegistrationRepository registrationRepository;

  public void sendWith(@Nonnull Registration registration, @Nonnull List<Attachment> attachments) {
    internalNotificationService.send(
        Notification.builder()
            .message(message(registration, attachments))
            .beforeSend(new RegistrationCallback.Before(registration, registrationRepository))
            .afterSend(new RegistrationCallback.After(registrationRepository))
            .build()
    );
  }

  @Nonnull
  private NotificationMessage message(@Nonnull Registration registration, @Nonnull List<Attachment> attachments) {
    return NotificationMessage.builder()
        .topic(INTERNSHIP_REGISTRATION_REQUEST.getKey())
        .subject(subject(registration))
        .values(values(registration))
        .attachments(attachments)
        .build();
  }

  @Nonnull
  private String subject(@Nonnull Registration registration) {

    final var applicantName = registration.getApplicantName();
    final var submitDate = SIMPLE_DATE_TIME.format(registration.getRegistrationDate());

    final var subject = I18n.of(SUBJECT_TEMPLATE_KEY, applicantName, submitDate);

    return registration.isSuspicious()
        ? I18n.of(SUSPICIOUS_PREFIX_KEY, subject)
        : subject;
  }

  @Getter
  @RequiredArgsConstructor
  private enum ReportAttributes {

    APPLICANT_NAME("applicantName"),
    INTERNSHIP_FIELD("internshipField"),
    EMAIL("email"),
    PHONE("phone"),
    ABOUT("about"),
    REGISTRATION_DATE("registrationDate"),
    TELEGRAM("telegram"),
    BIRTH_DATE("birthDate"),
    CITY("city"),
    UNIVERSITY("university"),
    EDU_STAGE("eduStage"),
    EDU_YEAR("eduYear"),
    SKILLS("skills"),
    DESIRED_TASKS("desiredTasks"),
    PORTFOLIO("portfolio"),
    EXPERIENCE("experience"),
    SUSPICIOUS("suspicious"),
    WORK_HOURS("workHours");

    private final String key;

    @Override
    public String toString() {
      return key;
    }

    public static Values mapValues(@Nonnull Registration registration,
                                   @Nonnull String city,
                                   @Nonnull String university) {
      return new Values()
          .put(APPLICANT_NAME, registration.getApplicantName())
          .put(INTERNSHIP_FIELD, registration.getInternshipField())
          .put(EMAIL, registration.getEmail())
          .put(PHONE, registration.getPhone())
          .put(ABOUT, registration.getAbout())
          .put(REGISTRATION_DATE, SIMPLE_DATE_TIME.format(registration.getRegistrationDate()))
          .put(TELEGRAM, registration.getTelegram())
          .put(BIRTH_DATE, SIMPLE_DATE.format(registration.getBirthDate()))
          .put(CITY, city)
          .put(UNIVERSITY, university)
          .put(EDU_STAGE, registration.getEducationStage())
          .put(EDU_YEAR, registration.getEducationYear())
          .put(SKILLS, registration.getAboutSkills())
          .put(DESIRED_TASKS, registration.getAboutWishedTasks())
          .put(PORTFOLIO, registration.getAboutPortfolio())
          .put(EXPERIENCE, registration.getAboutExperience())
          .put(SUSPICIOUS, registration.isSuspicious())
          .put(WORK_HOURS, registration.getWorkHours())
          ;
    }
  }

  @Nonnull
  private Values values(@Nonnull Registration registration) {
    return ReportAttributes.mapValues(
        registration,
        city(registration),
        university(registration)
    );
  }

  @Nonnull
  private String city(@Nonnull Registration registration) {
    return isNotBlank(registration.getCityCustom())
        ? registration.getCityCustom()
        : cityService.getCityWithRegionByIdOrEmpty(registration.getCityId());
  }

  @Nonnull
  private String university(@Nonnull Registration registration) {
    return isNotBlank(registration.getUniversityCustom())
        ? registration.getUniversityCustom()
        : universityService.findUniversityFullNameByIdOrEmpty(registration.getUniversityId());
  }
}
