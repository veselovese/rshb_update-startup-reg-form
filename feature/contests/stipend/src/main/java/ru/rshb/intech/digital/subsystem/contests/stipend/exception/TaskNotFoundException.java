package ru.rshb.intech.digital.subsystem.contests.stipend.exception;

import ru.rshb.intech.digital.commons.utils.exceptiion.DigitalServiceException;
import ru.rshb.intech.digital.messages.I18n;

/**
 * Задача не найдена.
 */
public class TaskNotFoundException extends DigitalServiceException {

  private static final String MSG_KEY = "contest.stipend.solution.validation.error.task.not.found";

  public TaskNotFoundException(String taskId) {
    super(I18n.of(MSG_KEY, taskId));
  }
}
