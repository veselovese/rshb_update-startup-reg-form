package ru.rshb.intech.digital.notifications.internal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InternalNotificationTopic {

  INTERNSHIP_REGISTRATION_REQUEST("internship.participation.request"),

  FEEDBACK_REPORT("feedback.report"),

  CHEF_PARTICIPANT_REQUEST("chef.participant.request"),

  INNOVATIONS_GENERAL_REQUEST("innovations.general.request"),

  INNOVATIONS_WINDOW_DEMAND("innovations.window.demand"),

  INNOVATIONS_WINDOW_PROPOSAL("innovations.window.proposal"),

  CROSSWORD_PARTICIPANT_REQUEST("crossword.participant.request"),

  STIPEND_SOLUTION_REQUEST("stipend.contest.solution.request"),

  STIPEND_QUESTION_REQUEST("stipend.contest.question.request"),

  FORM_USAGE_STATISTICS("internal.statistics.form.usage.report"),

  BLOGS_EXPERT_REGISTRATION_REQUEST("blogs.expert.registration.request")

  ;

  private final String key;
}
