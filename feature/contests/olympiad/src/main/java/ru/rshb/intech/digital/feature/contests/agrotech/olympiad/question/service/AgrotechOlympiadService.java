package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadQuestion;

public interface AgrotechOlympiadService {

  @Nonnull
  AgrotechOlympiadQuestion save(@Nonnull AgrotechOlympiadQuestion question);

  boolean isQuestionsLimitExceeded(@Nonnull String email);
}
