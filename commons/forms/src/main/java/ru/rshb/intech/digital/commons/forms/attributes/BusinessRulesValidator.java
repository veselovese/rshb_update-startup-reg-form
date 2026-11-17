package ru.rshb.intech.digital.commons.forms.attributes;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;

/**
 * Валидатор соответствия значения атрибута бизнес-правилам (бизнес-логика).
 *
 * @param <D> Тип входных данных формы (DTO).
 * @param <E> Выходной тип данных формы (Entity).
 * @param <EV> Тип выходных данных атрибута.
 */
@FunctionalInterface
public interface BusinessRulesValidator<D, E, EV> {

  void validate(EV value, @Nonnull FormContext<D, E> context) throws AttributeBusinessRulesException;

}
