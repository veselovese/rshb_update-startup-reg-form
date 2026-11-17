package ru.rshb.intech.digital.censor.forms;

import ru.rshb.intech.digital.commons.forms.attributes.PreCheck;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckResult;

import jakarta.annotation.Nonnull;

public class CensorPreCheck<D, E> extends PreCheck<D, String, E> {

  public CensorPreCheck(@Nonnull CensorAnalyzer censorAnalyzer) {
    super(censorAnalyzer);

    setHandler(PreCheckResult.SUSPICIOUS, (value, context) -> context.setPreCheckResult(PreCheckResult.SUSPICIOUS));
    setHandler(PreCheckResult.VIOLATION, (value, context) -> context.setPreCheckResult(PreCheckResult.VIOLATION));
  }
}
