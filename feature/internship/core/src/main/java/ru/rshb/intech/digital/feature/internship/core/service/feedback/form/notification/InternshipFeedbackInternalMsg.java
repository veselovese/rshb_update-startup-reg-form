package ru.rshb.intech.digital.feature.internship.core.service.feedback.form.notification;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipFeedback;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;

import java.util.List;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE_TIME;

@RequiredArgsConstructor
public class InternshipFeedbackInternalMsg extends NotificationMessage {

  private static final String TOPIC_ID = "internship.feedback.report";
  private static final String SUBJECT_TEMPLATE_KEY = "internship.feedback.internal.notification.subject";

  private final InternshipFeedback feedback;
  private final List<Attachment> attachments;

  @Override
  public String getTopic() {
    return TOPIC_ID;
  }

  @Override
  public String getSubject() {
    return I18n.of(
        SUBJECT_TEMPLATE_KEY,
        feedback.getTraineeName(),
        SIMPLE_DATE_TIME.format(feedback.getSubmitDate())
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
    TASKS_GRADE("tasksGrade"),
    SKILLS("skills"),
    MENTOR("mentor"),
    MENTOR_GRADE("mentorWorkGrade"),
    TEAM_AND_DIVISION("teamAndDivision"),
    TEAM_COMM_GRADE("teamCommunicationGrade"),
    EXPECTATION_LEVEL_GRADE("expectationLevelGrade"),
    COMMENT("comment"),
    TRAINEE_NAME("traineeName"),
    PHONE("phone"),
    EMAIL("email"),
    RECOMMENDATION_SRC("recommendationSource"),
    RECOMMENDATION("recommendation"),
    REVIEW("review");

    private final String key;


    @Override
    public String toString() {
      return key;
    }

    @Nonnull
    public static Values mapValues(@Nonnull InternshipFeedback feedback) {
      return new Values()
          .put(SUSPICIOUS, feedback.getSuspicious())
          .put(SUBMIT_DATE, SIMPLE_DATE_TIME.format(feedback.getSubmitDate()))
          .put(TASKS_GRADE, feedback.getTasksGrade())
          .put(SKILLS, feedback.getSkills())
          .put(MENTOR, feedback.getMentor())
          .put(MENTOR_GRADE, feedback.getMentorGrade())
          .put(TEAM_AND_DIVISION, feedback.getTeamDivision())
          .put(TEAM_COMM_GRADE, feedback.getTeamCommunicationGrade())
          .put(EXPECTATION_LEVEL_GRADE, feedback.getExpectationLevelGrade())
          .put(COMMENT, feedback.getComment())
          .put(TRAINEE_NAME, feedback.getTraineeName())
          .put(PHONE, feedback.getPhone())
          .put(EMAIL, feedback.getEmail())
          .put(RECOMMENDATION_SRC, feedback.getRecommendationSource())
          .put(RECOMMENDATION, feedback.getRecommendation())
          .put(REVIEW, feedback.getReview());
    }
  }

  @Override
  public Values getValues() {
    return ReportAttributes.mapValues(feedback);
  }

  @Override
  public List<Attachment> getAttachments() {
    return attachments;
  }
}
