package ru.rshb.intech.digital.subsystem.contests.stipend.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendContestSolution;
import ru.rshb.intech.digital.subsystem.contests.stipend.repository.StipendContestSolutionRepository;
import ru.rshb.intech.digital.subsystem.contests.stipend.repository.StipendContestTaskRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class StipendContestSolutionServiceImpl implements StipendContestSolutionService {

  private static final long MAX_SOLUTIONS_NUM_PER_USER = 20;
  private static final String[] ATTACHMENT_ALLOWED_EXTENSIONS = {"ppt", "pptx", "doc", "docx", "pdf"};


  private final StipendContestSolutionRepository solutionRepository;
  private final StipendContestTaskRepository taskRepository;


  @Override
  public boolean isTaskExist(@Nonnull String taskId) {
    return taskRepository.existsByTaskIdIgnoreCase(taskId);
  }

  @Nonnull
  @Override
  @Transactional
  public StipendContestSolution save(@Nonnull StipendContestSolution solution) {
    solution.setSubmitDate(LocalDateTime.now());
    final var savedSolution = solutionRepository.save(solution);
    log.info("New stipend contest solution has been saved");
    return savedSolution;
  }

  @Override
  public boolean isMaxSolutionsPerUserExceededByEmail(@Nonnull String taskId, @Nonnull String email) {
    final var existingSolutionsCount = solutionRepository.countByTaskIdAndEmail(taskId, email);
    return (existingSolutionsCount >= MAX_SOLUTIONS_NUM_PER_USER);
  }

  @Override
  public boolean isMaxSolutionsPerUserExceededByPhone(@Nonnull String taskId, @Nonnull String phone) {
    final var existingSolutionsCount = solutionRepository.countByTaskIdAndPhone(taskId, phone);
    return (existingSolutionsCount >= MAX_SOLUTIONS_NUM_PER_USER);
  }

  @Nonnull
  @Override
  public String[] getFormAttachmentAllowedExtensions() {
    return ATTACHMENT_ALLOWED_EXTENSIONS;
  }
}
