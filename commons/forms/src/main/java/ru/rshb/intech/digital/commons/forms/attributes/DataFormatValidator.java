package ru.rshb.intech.digital.commons.forms.attributes;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;

@FunctionalInterface
public interface DataFormatValidator<D, DV, E> {

  <A extends AttributeParams<D, DV, E, ?>> void validate(DV value, @Nonnull A attrProps, @Nonnull FormContext<D, E> context)
      throws AttributeValueMissingException, AttributeBadFormatException;
}
