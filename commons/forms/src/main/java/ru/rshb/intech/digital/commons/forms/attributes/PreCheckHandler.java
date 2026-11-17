package ru.rshb.intech.digital.commons.forms.attributes;

import ru.rshb.intech.digital.commons.forms.context.FormContext;

public interface PreCheckHandler<D, DV, E> {

  void handle(DV value, FormContext<D, E> context);

}
