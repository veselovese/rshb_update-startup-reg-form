package ru.rshb.intech.digital.subsystem.contests.stipend.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendContestQuestion;

public interface StipendContestQuestionService {

  @Nonnull
  StipendContestQuestion save(@Nonnull StipendContestQuestion question);

  boolean isMaxQuestionsPerUserExceededByEmail(@Nonnull String email);

  boolean isMaxQuestionsPerUserExceededByPhone(@Nonnull String phone);
}
