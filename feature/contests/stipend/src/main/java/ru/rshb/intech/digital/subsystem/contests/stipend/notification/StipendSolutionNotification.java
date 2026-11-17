package ru.rshb.intech.digital.subsystem.contests.stipend.notification;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.Notification;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;
import ru.rshb.intech.digital.notifications.api.NotificationServiceAdapter;
import ru.rshb.intech.digital.notifications.internal.InternalNotificationTopic;
import ru.rshb.intech.digital.notifications.internal.service.InternalNotificationService;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendContestSolution;
import ru.rshb.intech.digital.subsystem.contests.stipend.repository.StipendContestTaskRepository;

import java.util.List;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE_TIME;

@Component
@RequiredArgsConstructor
public class StipendSolutionNotification implements NotificationServiceAdapter<StipendContestSolution> {

  private static final String SUBJECT_TEMPLATE_KEY = "contest.stipend.solution.request.email.subject";
  private static final String SUSPICIOUS_PREFIX_TEMPLATE_KEY = "common.check.status.suspicious";

  private final InternalNotificationService internalNotificationService;
  private final StipendContestTaskRepository taskRepository;

  @Async
  @Override
  public void send(@Nonnull StipendContestSolution solution, @Nonnull List<Attachment> attachments) {
    internalNotificationService.send(
        new Notification(message(solution, attachments))
    );
  }

  @Nonnull
  private NotificationMessage message(@Nonnull StipendContestSolution solution, @Nonnull List<Attachment> attachment) {
    return NotificationMessage.builder()
        .topic(InternalNotificationTopic.STIPEND_SOLUTION_REQUEST.getKey())
        .subject(subject(solution))
        .values(values(solution))
        .attachments(attachment)
        .build();
  }

  @Nonnull
  private String subject(@Nonnull StipendContestSolution solution) {

    final var subject = I18n.of(
        SUBJECT_TEMPLATE_KEY,
        solution.getParticipantName(),
        SIMPLE_DATE_TIME.format(solution.getSubmitDate())
    );

    return solution.getSuspicious()
        ? I18n.of(SUSPICIOUS_PREFIX_TEMPLATE_KEY, subject)
        : subject;
  }

  @Getter
  @RequiredArgsConstructor
  private enum ReportAttributes {

    SUBMIT_DATE("submitDate"),
    PARTICIPANT_NAME("participantName"),
    PHONE("phone"),
    EMAIL("email"),
    COMMENT("comment"),
    TASK("task"),
    SUSPICIOUS("suspicious")
    ;

    private final String key;

    @Override
    public String toString() {
      return key;
    }

    @Nonnull
    public static Values mapValues(@Nonnull StipendContestSolution solution, @Nonnull String taskTitle) {
      return new Values()
          .put(SUBMIT_DATE, SIMPLE_DATE_TIME.format(solution.getSubmitDate()))
          .put(PARTICIPANT_NAME, solution.getParticipantName())
          .put(PHONE, solution.getPhone())
          .put(EMAIL, solution.getEmail())
          .put(COMMENT, solution.getComment())
          .put(TASK, taskTitle)
          .put(SUSPICIOUS, solution.getSuspicious());
    }
  }

  @Nonnull
  private Values values(@Nonnull StipendContestSolution solution) {

    final var taskTitle = taskRepository.findByTaskIdAndEnabledTrue(solution.getTaskId())
        .map(t -> t.getTaskName())
        .orElse(StringUtils.EMPTY);

    return ReportAttributes.mapValues(solution, taskTitle);
  }
}
