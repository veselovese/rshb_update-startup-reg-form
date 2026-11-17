package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadQuestion;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.repository.AgrotechOlympiadQuestionRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AgrotechOlympiadServiceImpl implements AgrotechOlympiadService {

  public static final int MAX_QUESTIONS_NUMBER_PER_USER = 20;

  private final AgrotechOlympiadQuestionRepository questionRepository;

  @Nonnull
  @Override
  @Transactional
  public AgrotechOlympiadQuestion save(@Nonnull AgrotechOlympiadQuestion question) {
    question.setSubmitDate(LocalDateTime.now());
    final var savedQuestion = questionRepository.save(question);
    log.info("New Agrotech Olympiad question has been saved");
    return savedQuestion;
  }

  @Override
  public boolean isQuestionsLimitExceeded(@Nonnull String email) {
    final var questionNumInDb = questionRepository.countAllByEmailIsIgnoreCase(email);
    return (questionNumInDb >= MAX_QUESTIONS_NUMBER_PER_USER);
  }
}
