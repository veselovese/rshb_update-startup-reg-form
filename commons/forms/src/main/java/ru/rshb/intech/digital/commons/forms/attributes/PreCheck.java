package ru.rshb.intech.digital.commons.forms.attributes;

import ru.rshb.intech.digital.commons.forms.context.FormContext;

import jakarta.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class PreCheck<D, DV, E> {

  private final Map<PreCheckResult, PreCheckHandler<D, DV, E>> handlers;
  private final PreCheckAnalyzer<DV> analyzer;

  public PreCheck(@Nonnull PreCheckAnalyzer<DV> analyzer) {
    this.handlers = new HashMap<>();
    this.analyzer = analyzer;
    setHandler(PreCheckResult.OK, (value, context) -> {}); // bypass for OK by default
  }

  public void setHandler(@Nonnull PreCheckResult result, @Nonnull PreCheckHandler<D, DV, E> handler) {
    this.handlers.put(result, handler);
  }

  @Nonnull
  public PreCheckResult process(DV rawValue, @Nonnull FormContext<D, E> context) {

    final var result = analyzer.analyze(rawValue);
    final var handler = handlers.get(result);
    handler.handle(rawValue, context);
    return result;
  }

  public static class BypassedPreCheck<D, DV, E> extends PreCheck<D, DV, E> {

    public BypassedPreCheck() {
      super(value -> PreCheckResult.OK);
    }
  }

}
