package ru.rshb.intech.digital.subsystem.contests.stipend.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendContestSolution;

public interface StipendContestSolutionService {

  boolean isTaskExist(@Nonnull String taskId);

  @Nonnull
  StipendContestSolution save(@Nonnull StipendContestSolution solution);

  boolean isMaxSolutionsPerUserExceededByEmail(@Nonnull String taskId, @Nonnull String email);

  boolean isMaxSolutionsPerUserExceededByPhone(@Nonnull String taskId, @Nonnull String phone);

  @Nonnull
  String[] getFormAttachmentAllowedExtensions();
}
