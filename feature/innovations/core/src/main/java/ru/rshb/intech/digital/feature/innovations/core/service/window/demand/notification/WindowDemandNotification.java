package ru.rshb.intech.digital.feature.innovations.core.service.window.demand.notification;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowDemand;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.Notification;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;
import ru.rshb.intech.digital.notifications.api.NotificationServiceAdapter;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;

import java.util.List;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE_TIME;
import static ru.rshb.intech.digital.notifications.internal.InternalNotificationTopic.INNOVATIONS_WINDOW_DEMAND;

@Component
@RequiredArgsConstructor
public class WindowDemandNotification implements NotificationServiceAdapter<InnovationWindowDemand> {

  private static final String SUBJECT_TEMPLATE_KEY = "innovations.proposal.email.subject";
  private static final String SUSPICIOUS_PREFIX_KEY = "common.check.status.suspicious";

  private final InternalNotificationService internalNotificationService;


  @Override
  public void send(@Nonnull InnovationWindowDemand data, @Nonnull List<Attachment> attachments) {
    internalNotificationService.send(new Notification(message(data, attachments)));
  }

  @Nonnull
  private NotificationMessage message(@Nonnull InnovationWindowDemand demand, @Nonnull List<Attachment> attachments) {
    return NotificationMessage.builder()
        .topic(INNOVATIONS_WINDOW_DEMAND.getKey())
        .subject(subject(demand))
        .values(ReportAttributes.mapValues(demand))
        .attachments(attachments)
        .build();
  }

  @Nonnull
  private String subject(@Nonnull InnovationWindowDemand demand) {

    final var companyName = demand.getCompanyName();
    final var submitDate = SIMPLE_DATE_TIME.format(demand.getSubmitDate());

    final var subject = I18n.of(SUBJECT_TEMPLATE_KEY, companyName, submitDate);

    return demand.isSuspicious()
        ? I18n.of(SUSPICIOUS_PREFIX_KEY, subject)
        : subject;
  }

  @Getter
  @RequiredArgsConstructor
  private enum ReportAttributes {

    SUBMIT_DATE("submitDate"),
    COMPANY_NAME("companyName"),
    COMPANY_SITE("companySite"),
    SUMMARY("summary"),
    DESCRIPTION("description"),
    PHONE("phone"),
    EMAIL("email"),
    SUSPICIOUS("suspicious");

    private final String key;

    @Override
    public String toString() {
      return key;
    }

    @Nonnull
    public static Values mapValues(@Nonnull InnovationWindowDemand demand) {
      return new Values()
          .put(SUBMIT_DATE, SIMPLE_DATE_TIME.format(demand.getSubmitDate()))
          .put(COMPANY_NAME, demand.getCompanyName())
          .put(COMPANY_SITE, demand.getCompanySite())
          .put(SUMMARY, demand.getSummary())
          .put(DESCRIPTION, demand.getDescription())
          .put(PHONE, demand.getPhone())
          .put(EMAIL, demand.getEmail())
          .put(SUSPICIOUS, demand.isSuspicious());
    }
  }
}
