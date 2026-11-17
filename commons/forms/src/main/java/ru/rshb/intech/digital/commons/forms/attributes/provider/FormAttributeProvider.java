package ru.rshb.intech.digital.commons.forms.attributes.provider;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.forms.Form;
import ru.rshb.intech.digital.commons.forms.attributes.Attribute;

import java.util.List;

/**
 * Поставщик атрибутов формы.
 */
public interface FormAttributeProvider {

  /**
   * Возвращает инстансы атрибутов формы по ее классу.
   *
   * @param formClass Класс формы искомых атрибутов.
   * @return Список с инстансами атрибутов.
   * @param <D> Тип входных данных (DTO).
   * @param <E> Выходной тип данных (Entity).
   */
  @Nonnull
  <D, E> List<Attribute<D, ?, E, ?>> findByFormClass(@Nonnull Class<? extends Form> formClass);

}
