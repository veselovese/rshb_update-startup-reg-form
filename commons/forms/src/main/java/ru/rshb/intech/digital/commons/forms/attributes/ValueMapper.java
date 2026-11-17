package ru.rshb.intech.digital.commons.forms.attributes;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;

public interface ValueMapper<DV, EV> {

  <D, E> EV map(DV rawValue, @Nonnull FormContext<D, E> context)
      throws AttributeBusinessRulesException, AttributeBadFormatException;
}
