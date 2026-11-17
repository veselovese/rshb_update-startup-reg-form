package ru.rshb.intech.digital.censor.api;

import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.censor.api.exception.CensorUndefinedException;
import ru.rshb.intech.digital.censor.api.exception.CensorViolationException;

import java.util.function.BiConsumer;

@RequiredArgsConstructor
public class CensorValidator implements BiConsumer<String, String> {

  private final CensorService censorService;

  @Override
  public void accept(String fieldName, String value) {
    final var result = censorService.validate(value);
    switch (result) {
      case VIOLATION -> throw new CensorViolationException(fieldName, value);
      case UNDEFINED -> throw new CensorUndefinedException(fieldName, value);
    }
  }
}
