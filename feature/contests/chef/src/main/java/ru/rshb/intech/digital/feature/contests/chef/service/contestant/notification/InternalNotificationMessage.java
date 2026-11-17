package ru.rshb.intech.digital.feature.contests.chef.service.contestant.notification;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.contests.chef.entity.ChefContestant;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;

import java.util.List;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE_TIME;
import static ru.rshb.intech.digital.notifications.internal.InternalNotificationTopic.CHEF_PARTICIPANT_REQUEST;

@RequiredArgsConstructor
public class InternalNotificationMessage extends NotificationMessage {

  private static final String SUBJECT_TEMPLATE_KEY = "contest.chef.result.email.subject";
  private static final String SUSPICIOUS_PREFIX_TEMPLATE_KEY = "common.check.status.suspicious";

  private final ChefContestant contestant;
  private final List<Attachment> attachments;


  @Override
  public String getTopic() {
    return CHEF_PARTICIPANT_REQUEST.getKey();
  }

  @Override
  public String getSubject() {

    final var subject = I18n.of(SUBJECT_TEMPLATE_KEY, contestant.getName());

    return contestant.isSuspicious()
        ? I18n.of(SUSPICIOUS_PREFIX_TEMPLATE_KEY, subject)
        : subject;
  }

  @Override
  public boolean isUseDefaultSubject() {
    return false;
  }

  @Getter
  @RequiredArgsConstructor
  private enum ReportValues {

    SUBMIT_DATE("submitDate"),
    CONTESTANT_NAME("contestantName"),
    PHONE("phone"),
    SOLUTION_URL("solutionUrl"),
    CONTEST_RESULT("contestResult"),
    SUSPICIOUS("suspicious")
    ;

    private final String key;

    @Override
    public String toString() {
      return key;
    }

    @Nonnull
    public static Values mapValues(@Nonnull ChefContestant contestant) {
      return new Values()
          .put(SUBMIT_DATE, SIMPLE_DATE_TIME.format(contestant.getSubmitDate()))
          .put(CONTESTANT_NAME, contestant.getName())
          .put(PHONE, contestant.getPhone())
          .put(SOLUTION_URL, contestant.getSolutionUrl())
          .put(CONTEST_RESULT, contestant.getResult() + "%")
          .put(SUSPICIOUS, contestant.isSuspicious());
    }
  }

  @Override
  public Values getValues() {
    return ReportValues.mapValues(contestant);
  }

  @Override
  public List<Attachment> getAttachments() {
    return attachments;
  }
}
