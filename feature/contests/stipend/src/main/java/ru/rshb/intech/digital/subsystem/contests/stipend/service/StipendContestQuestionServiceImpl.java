package ru.rshb.intech.digital.subsystem.contests.stipend.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendContestQuestion;
import ru.rshb.intech.digital.subsystem.contests.stipend.repository.StipendContestQuestionRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class StipendContestQuestionServiceImpl implements StipendContestQuestionService {

  private static final long MAX_QUESTIONS_NUM_PER_USER = 20;

  private final StipendContestQuestionRepository questionRepository;
  
  @Nonnull
  @Override
  @Transactional
  public StipendContestQuestion save(@Nonnull StipendContestQuestion question) {
    question.setSubmitDate(LocalDateTime.now());
    final var savedQuestion = questionRepository.save(question);
    log.info("New stipend contest question has been saved");
    return savedQuestion;
  }

  @Override
  public boolean isMaxQuestionsPerUserExceededByEmail(@Nonnull String email) {
    final var existingQuestionsCount = questionRepository.countByEmail(email);
    return (existingQuestionsCount >= MAX_QUESTIONS_NUM_PER_USER);
  }

  @Override
  public boolean isMaxQuestionsPerUserExceededByPhone(@Nonnull String phone) {
    final var existingQuestionsCount = questionRepository.countByPhone(phone);
    return (existingQuestionsCount >= MAX_QUESTIONS_NUM_PER_USER);
  }
}
