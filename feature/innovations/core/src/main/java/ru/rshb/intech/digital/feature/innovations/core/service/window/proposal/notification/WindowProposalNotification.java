package ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.notification;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.content.model.Innovation;
import ru.rshb.intech.digital.content.provider.api.InnovationContentService;
import ru.rshb.intech.digital.feature.innovations.core.entity.InnovationWindowProposal;
import ru.rshb.intech.digital.feature.innovations.core.exception.DemandNotFoundException;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.Notification;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;
import ru.rshb.intech.digital.notifications.api.NotificationServiceAdapter;
import ru.rshb.intech.digital.notifications.internal.InternalNotificationTopic;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;

import java.util.List;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE_TIME;

@Component
@RequiredArgsConstructor
public class WindowProposalNotification implements NotificationServiceAdapter<InnovationWindowProposal> {

  private static final String SUBJECT_TEMPLATE_KEY = "innovations.solution.email.subject";
  private static final String SUSPICIOUS_PREFIX_KEY = "common.check.status.suspicious";

  private final InternalNotificationService internalNotificationService;
  private final InnovationContentService innovationContentService;

  @Value("${external.url}")
  private String externalUrl;


  @Override
  public void send(@Nonnull InnovationWindowProposal data, @Nonnull List<Attachment> attachments) {
    internalNotificationService.send(new Notification(message(data, attachments)));
  }

  @Nonnull
  private NotificationMessage message(@Nonnull InnovationWindowProposal proposal, @Nonnull List<Attachment> attachments) {
    return NotificationMessage.builder()
        .topic(InternalNotificationTopic.INNOVATIONS_WINDOW_PROPOSAL.getKey())
        .subject(subject(proposal))
        .values(values(proposal))
        .attachments(attachments)
        .build();
  }

  @Nonnull
  private String subject(@Nonnull InnovationWindowProposal proposal) {

    final var startupName = proposal.getStartupName();
    final var submitDate = SIMPLE_DATE_TIME.format(proposal.getSubmitDate());

    final var subject = I18n.of(SUBJECT_TEMPLATE_KEY, startupName, submitDate);

    return proposal.isSuspicious()
        ? I18n.of(SUSPICIOUS_PREFIX_KEY, subject)
        : subject;
  }

  @Getter
  @RequiredArgsConstructor
  private enum ReportAttributes {

    SUBMIT_DATE("submitDate"),
    DEMAND_URL("demandUrl"),
    DESCRIPTION("description"),
    STARTUP_LINK("startupLink"),
    STARTUP_NAME("startupName"),
    REPRESENTATIVE_NAME("representativeName"),
    PHONE("phone"),
    EMAIL("email"),
    SUSPICIOUS("suspicious");

    private final String key;

    @Override
    public String toString() {
      return key;
    }

    @Nonnull
    public static Values mapValues(@Nonnull InnovationWindowProposal proposal,
                                   @Nonnull String siteUrl) {
      return new Values()
          .put("submitDate", SIMPLE_DATE_TIME.format(proposal.getSubmitDate()))
          .put("demandUrl", siteUrl)
          .put("description", proposal.getDescription())
          .put("startupLink", proposal.getStartupLink())
          .put("startupName", proposal.getStartupName())
          .put("representativeName", proposal.getRepresentativeName())
          .put("phone", proposal.getPhone())
          .put("email", proposal.getEmail())
          .put("suspicious", proposal.isSuspicious())
          ;
    }
  }

  @Nonnull
  private Values values(@Nonnull InnovationWindowProposal proposal) {

    final var demandUrl = innovationContentService.findById(proposal.getDemandCardId())
        .map(demand -> demandUrl(externalUrl, demand))
        .orElseThrow(() -> new DemandNotFoundException(proposal.getDemandCardId()));

    return ReportAttributes.mapValues(proposal, demandUrl);
  }

  @Nonnull
  private String demandUrl(@Nonnull String externalUrl, @Nonnull Innovation demandCard) {
    return String.format("%s/window-innovation/%s", externalUrl, demandCard.getSlug());
  }
}
