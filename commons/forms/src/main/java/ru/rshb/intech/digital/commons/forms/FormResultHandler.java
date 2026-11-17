package ru.rshb.intech.digital.commons.forms;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.forms.context.FormContext;

@FunctionalInterface
public interface FormResultHandler<D, E> {

  @Nonnull
  E handleResult(@Nonnull E result, @Nonnull FormContext<D, E> context);

}
