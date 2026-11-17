package ru.rshb.intech.digital.commons.forms.attributes.number;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.forms.attributes.Attribute;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.DataFormatValidator;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.messages.I18n;

/**
 * Частичная реализация обработчика атрибута со значением типа Integer.
 *
 * @param <D> Тип входных данных (DTO).
 * @param <E> Выходной тип данных (Entity).
 */
public abstract class IntegerAttributeBase<D, E> extends Attribute<D, Integer, E, Integer> {

  /**
   * Конструктор обработчика атрибута с типом данных Integer.
   *
   * @param titleKey ID наименования атрибута (i18n).
   * @param min Минимальное допустимое значение.
   * @param max Максимальное допустимое значение.
   */
  public IntegerAttributeBase(@Nonnull String titleKey, int min, int max) {
    this(titleKey, min, max, false);
  }

  /**
   * Конструктор обработчика атрибута с типом данных Integer.
   *
   * @param titleKey ID наименования атрибута (i18n).
   * @param min Минимальное допустимое значение.
   * @param max Максимальное допустимое значение.
   * @param nullable если <code>true</code>, то <code>null</code> - допустимое входное значение.
   */
  public IntegerAttributeBase(@Nonnull String titleKey, int min, int max, boolean nullable) {
    super(
        IntegerAttributeParams.Builder
            .<D, E>create()
            .name(I18n.of(titleKey))
            .nullable(nullable)
            .min(min)
            .max(max)
            .valueMapper(new ThroughValueMapper())
            .dataFormatValidator(new IntegerDataFormatValidator<>())
            .build()
    );
  }

  /**
   * Конструктор обработчика атрибута с типом данных Integer.
   *
   * @param titleKey ID наименования атрибута (i18n).
   * @param min Минимальное допустимое значение.
   * @param brValidator кастомный валидатор бизнес-правил.
   */
  public IntegerAttributeBase(@Nonnull String titleKey, int min,
                              @Nonnull BusinessRulesValidator<D, E, Integer> brValidator) {
    super(
        IntegerAttributeParams.Builder
            .<D, E>create()
            .name(I18n.of(titleKey))
            .nullable(false)
            .min(min)
            .valueMapper(new ThroughValueMapper())
            .dataFormatValidator(new IntegerDataFormatValidator<>())
            .businessRulesValidator(brValidator)
            .build()
    );
  }

  public static class ThroughValueMapper implements ValueMapper<Integer, Integer> {

    @Override
    public <D, E> Integer map(Integer rawValue, @Nonnull FormContext<D, E> context) {
      return rawValue;
    }
  }


  private static class IntegerDataFormatValidator<D, E> implements DataFormatValidator<D, Integer, E> {

    @Override
    @SuppressWarnings("unchecked")
    public <A extends AttributeParams<D, Integer, E, ?>> void validate(Integer value,
                                                                       @Nonnull A attrProps,
                                                                       @Nonnull FormContext<D, E> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      final var params = (IntegerAttributeParams<D, E>) attrProps;

      assertNotNullIfNonnull(params, value);

      if (value != null) {

        if (params.min().isPresent()) {
          assertGreaterOrEqual(params.getName(), params.min().get(), value);
        }

        if (params.max().isPresent()) {
          assertLessOrEqual(params.getName(), params.max().get(), value);
        }
      }
    }

    private void assertNotNullIfNonnull(@Nonnull IntegerAttributeParams<D, E> params, Integer value) {
      if (!params.isNullable() && value == null) {
        throw new AttributeValueMissingException(params.getName());
      }
    }

    private void assertGreaterOrEqual(@Nonnull String attrName, int er, int ar) {
      if (ar < er) {
        throw new AttributeBadFormatException(attrName);
      }
    }

    private void assertLessOrEqual(@Nonnull String attrName, int er, int ar) {
      if (ar > er) {
        throw new AttributeBadFormatException(attrName);
      }
    }
  }
}
