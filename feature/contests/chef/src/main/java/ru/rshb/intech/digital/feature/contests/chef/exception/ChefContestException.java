package ru.rshb.intech.digital.feature.contests.chef.exception;

import ru.rshb.intech.digital.commons.utils.exceptiion.DigitalServiceException;

public class ChefContestException extends DigitalServiceException {

  public ChefContestException(String message) {
    super(message);
  }

  public ChefContestException(String message, Throwable cause) {
    super(message, cause);
  }
}
