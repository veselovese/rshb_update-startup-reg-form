package ru.rshb.intech.digital.feature.innovations.core.service.general.notification;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.innovations.core.entity.GeneralRequest;
import ru.rshb.intech.digital.feature.innovations.core.repository.general.GeneralRequestRepository;
import ru.rshb.intech.digital.feature.innovations.core.service.general.notification.GeneralRequestCallback.After;
import ru.rshb.intech.digital.feature.innovations.core.service.general.notification.GeneralRequestCallback.Before;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.Notification;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;
import ru.rshb.intech.digital.notifications.api.NotificationServiceAdapter;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;

import java.util.List;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE_TIME;
import static ru.rshb.intech.digital.notifications.internal.InternalNotificationTopic.INNOVATIONS_GENERAL_REQUEST;

@Component
@RequiredArgsConstructor
public class GeneralRequestNotification implements NotificationServiceAdapter<GeneralRequest> {

  private static final String SUBJECT_TEMPLATE_KEY = "innovations.proposal.email.subject";
  private static final String SUSPICIOUS_PREFIX_KEY = "common.check.status.suspicious";

  private final InternalNotificationService internalNotificationService;
  private final GeneralRequestRepository requestRepository;


  @Override
  public void send(@Nonnull GeneralRequest request, @Nonnull List<Attachment> attachments) {
    internalNotificationService.send(
        Notification.builder()
            .message(message(request, attachments))
            .beforeSend(new Before(request, requestRepository))
            .afterSend(new After(requestRepository))
            .build()
    );
  }

  @Nonnull
  private NotificationMessage message(@Nonnull GeneralRequest data, @Nonnull List<Attachment> attachments) {
    return NotificationMessage.builder()
        .topic(INNOVATIONS_GENERAL_REQUEST.getKey())
        .subject(subject(data))
        .values(ReportAttributes.mapValues(data))
        .attachments(attachments)
        .build();
  }

  @Nonnull
  private String subject(@Nonnull GeneralRequest request) {

    final var applicantName = request.getApplicantName();
    final var submitDate = SIMPLE_DATE_TIME.format(request.getSubmitDate());

    final var subject = I18n.of(SUBJECT_TEMPLATE_KEY, applicantName, submitDate);

    return request.isSuspicious()
        ? I18n.of(SUSPICIOUS_PREFIX_KEY, subject)
        : subject;
  }

  @Getter
  @RequiredArgsConstructor
  private enum ReportAttributes {

    APPLICANT_NAME("applicantName"),
    EMAIL("email"),
    PHONE("phone"),
    ABOUT_PROPOSAL("aboutProposal"),
    SUBMIT_DATE("submitDate"),
    SUSPICIOUS("suspicious");

    private final String key;

    @Override
    public String toString() {
      return key;
    }

    @Nonnull
    public static Values mapValues(@Nonnull GeneralRequest request) {
      return new Values()
          .put(APPLICANT_NAME, request.getApplicantName())
          .put(EMAIL, request.getEmail())
          .put(PHONE, request.getPhone())
          .put(ABOUT_PROPOSAL, request.getProposal())
          .put(SUBMIT_DATE, SIMPLE_DATE_TIME.format(request.getSubmitDate()))
          .put(SUSPICIOUS, request.isSuspicious());
    }
  }
}
