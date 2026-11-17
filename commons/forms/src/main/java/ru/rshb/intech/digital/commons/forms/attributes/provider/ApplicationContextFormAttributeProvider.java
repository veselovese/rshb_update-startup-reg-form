package ru.rshb.intech.digital.commons.forms.attributes.provider;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import ru.rshb.intech.digital.commons.forms.Form;
import ru.rshb.intech.digital.commons.forms.attributes.Attribute;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Поставщик бинов атрибутов из контекста Spring, связанных с заданной формой.
 */
@Slf4j
@RequiredArgsConstructor
public class ApplicationContextFormAttributeProvider implements FormAttributeProvider {

  private final ApplicationContext applicationContext;

  /**
   * Ищет бины атрибутов по классу формы в контексте Spring'а и возвращает их.
   *
   * @param formClass Класс формы.
   * @return Список с инстансами атрибутов.
   * @param <D> Тип входных данных (DTO).
   * @param <E> Выходной тип данных (Entity).
   */
  @Nonnull
  @Override
  public <D, E> List<Attribute<D, ?, E, ?>> findByFormClass(@Nonnull Class<? extends Form> formClass) {

    final List<Attribute<D, ?, E, ?>> formAttributes = applicationContext.getBeansWithAnnotation(FormAttribute.class)
        .values()
        .stream()
        .map(new FormAttributeBeanMapper<D, E>())
        .filter(new FormAttributeBeanFilter<>(formClass))
        .sorted(new FormAttributeComparator<>())
        .toList();

    printDebugSummary(formAttributes);

    return formAttributes;
  }

  private <D, E> void printDebugSummary(List<Attribute<D, ?, E, ?>> attributes) {
    final var sb = new StringBuilder("Found [{" + attributes.size() + "}] attributes:\n");
    attributes.forEach(attr -> sb.append("\t")
        .append("- (").append(attr.processOrder())
        .append(") [")
        .append(attr.properties().getName())
        .append("]\n")
    );
    log.debug(sb.toString());
  }

  /**
   * Предикат для фильтрации бинов атрибутов по аннотации-маркеру {@link FormAttribute}
   *
   * @param <D> Тип входных данных (DTO).
   * @param <E> Выходной тип данных (Entity).
   */
  @RequiredArgsConstructor
  private static  class FormAttributeBeanFilter<D, E> implements Predicate<Attribute<D, ?, E, ?>> {

    private final Class<? extends Form> formClass;

    @Override
    public boolean test(Attribute<D, ?, E, ?> deAttribute) {
      final var attributeClass = deAttribute.getClass()
          .getAnnotation(FormAttribute.class)
          .value();
      return formClass.equals(attributeClass);
    }
  }

  private static class FormAttributeBeanMapper<D, E> implements Function<Object, Attribute<D, ?, E, ?>> {

    @Override
    @SuppressWarnings("unchecked")
    public Attribute<D, ?, E, ?> apply(Object bean) {
      return (Attribute<D, ?, E, ?>) bean;
    }
  }

  /**
   * Компаратор атрибутов. Нужен для установления порядка обработки атрибутов формы.
   *
   * @param <D> Тип входных данных (DTO).
   * @param <E> Выходной тип данных (Entity).
   */
  private static class FormAttributeComparator<D, E> implements Comparator<Attribute<D, ?, E, ?>> {

    @Override
    public int compare(Attribute<D, ?, E, ?> o1, Attribute<D, ?, E, ?> o2) {
      final int o1Order = o1.processOrder();
      final int o2Order = o2.processOrder();
      return Integer.compare(o1Order, o2Order);
    }
  }
}
