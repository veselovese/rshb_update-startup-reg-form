package ru.rshb.intech.digital.commons.forms.attributes.number;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.forms.attributes.Attribute;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.DataFormatValidator;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.messages.I18n;

/**
 * Частичная реализация обработчика атрибута со значением типа Long.
 *
 * @param <D> Тип входных данных (DTO).
 * @param <E> Выходной тип данных (Entity).
 */
public abstract class LongAttributeBase<D, E> extends Attribute<D, Long, E, Long> {


  public LongAttributeBase(@Nonnull String titleKey, boolean nullable, long min, long max) {
    super(
        LongAttributeParams.Builder
            .<D, E>create()
            .name(I18n.of(titleKey))
            .nullable(nullable)
            .min(min)
            .max(max)
            .valueMapper(new ThroughValueMapper())
            .dataFormatValidator(new LongDataFormatValidator<>())
            .build()
    );
  }


  private static class ThroughValueMapper implements ValueMapper<Long, Long> {

    @Override
    public <D, E> Long map(Long rawValue, @Nonnull FormContext<D, E> context) {
      return rawValue;
    }
  }

  private static class LongDataFormatValidator<D, E> implements DataFormatValidator<D, Long, E> {

    @Override
    @SuppressWarnings("unchecked")
    public <A extends AttributeParams<D, Long, E, ?>> void validate(Long value,
                                                                    @Nonnull A attrProps,
                                                                    @Nonnull FormContext<D, E> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      final var params = (LongAttributeParams<D, E>) attrProps;

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

    private void assertNotNullIfNonnull(@Nonnull LongAttributeParams<D, E> params, Long value) {
      if (!params.isNullable() && value == null) {
        throw new AttributeValueMissingException(params.getName());
      }
    }

    private void assertGreaterOrEqual(@Nonnull String attrName, long er, long ar) {
      if (ar < er) {
        throw new AttributeBadFormatException(attrName);
      }
    }

    private void assertLessOrEqual(@Nonnull String attrName, long er, long ar) {
      if (ar > er) {
        throw new AttributeBadFormatException(attrName);
      }
    }
  }
}
