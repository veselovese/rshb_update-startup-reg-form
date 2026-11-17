package ru.rshb.intech.digital.commons.forms.context;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckResult;

import jakarta.annotation.Nonnull;

@Getter
@RequiredArgsConstructor
public class FormContext<D, E> {

  private final String formName;

  private final D inputData;

  private final E outputData;

  @Setter
  private PreCheckResult preCheckResult = PreCheckResult.OK;

  @Setter
  private String errorAttributeName;

  public boolean is(@Nonnull PreCheckResult preCheckResult) {
    return this.preCheckResult == preCheckResult;
  }
}
