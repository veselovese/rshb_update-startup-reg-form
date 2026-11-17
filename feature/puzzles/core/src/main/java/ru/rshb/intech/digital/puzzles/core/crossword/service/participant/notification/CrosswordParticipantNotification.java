package ru.rshb.intech.digital.puzzles.core.crossword.service.participant.notification;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.Notification;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;
import ru.rshb.intech.digital.notifications.api.NotificationServiceAdapter;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordParticipantRepository;
import ru.rshb.intech.digital.puzzles.core.crossword.service.CrosswordCallback.After;
import ru.rshb.intech.digital.puzzles.core.crossword.service.CrosswordCallback.Before;
import ru.rshb.intech.digital.puzzles.entity.CrosswordParticipant;

import java.util.List;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE_TIME;
import static ru.rshb.intech.digital.notifications.internal.InternalNotificationTopic.CROSSWORD_PARTICIPANT_REQUEST;

@Component
@RequiredArgsConstructor
public class CrosswordParticipantNotification implements NotificationServiceAdapter<CrosswordParticipant> {

  private final InternalNotificationService internalNotificationService;
  private final CrosswordParticipantRepository participantRepository;

  @Override
  public void send(@Nonnull CrosswordParticipant data, @Nonnull List<Attachment> attachments) {
    internalNotificationService.send(
        Notification.builder()
            .message(message(data, attachments))
            .beforeSend(new Before(data, participantRepository))
            .afterSend(new After(participantRepository))
            .build()
    );
  }

  @Nonnull
  private NotificationMessage message(@Nonnull CrosswordParticipant participant, @Nonnull List<Attachment> attachments) {
    return NotificationMessage.builder()
        .topic(CROSSWORD_PARTICIPANT_REQUEST.getKey())
        .subject(subject(participant))
        .values(values(participant))
        .attachments(attachments)
        .build();
  }

  @Nonnull
  private String subject(@Nonnull CrosswordParticipant participant) {

    final var participantName = participant.getParticipantName();
    final var submitDate = SIMPLE_DATE_TIME.format(participant.getSubmitDate());

    final var subject = I18n.of("puzzle.crossword.email.subject", participantName, submitDate);

    return participant.isSuspicious()
        ? I18n.of("common.check.status.suspicious", subject)
        : subject;
  }

  @Nonnull
  private Values values(@Nonnull CrosswordParticipant participant) {
    return new Values()
        .put("participantName", participant.getParticipantName())
        .put("email", participant.getEmail())
        .put("phone", participant.getPhone())
        .put("about", participant.getAbout())
        .put("submitDate", SIMPLE_DATE_TIME.format(participant.getSubmitDate()))
        .put("suspicious", participant.isSuspicious());
  }
}
