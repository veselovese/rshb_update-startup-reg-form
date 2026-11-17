package ru.rshb.intech.digital.censor.forms;

import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.censor.api.CensorResult;
import ru.rshb.intech.digital.censor.api.CensorService;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckAnalyzer;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckResult;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import static org.apache.commons.lang3.StringUtils.isBlank;


@RequiredArgsConstructor
public class CensorAnalyzer implements PreCheckAnalyzer<String> {

  private final CensorService censorService;

  @Nonnull
  @Override
  public PreCheckResult analyze(@Nullable String value) {

    if (isBlank(value)) {
      return PreCheckResult.OK;
    }

    final var censorResult = censorService.validate(value);
    return mapResult(censorResult);
  }

  @Nonnull
  private PreCheckResult mapResult(@Nonnull CensorResult result) {
    return switch (result) {
      case OK -> PreCheckResult.OK;
      case UNDEFINED -> PreCheckResult.SUSPICIOUS;
      case VIOLATION -> PreCheckResult.VIOLATION;
    };
  }
}
