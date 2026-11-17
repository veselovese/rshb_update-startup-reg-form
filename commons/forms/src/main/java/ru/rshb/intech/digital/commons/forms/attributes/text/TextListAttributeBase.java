package ru.rshb.intech.digital.commons.forms.attributes.text;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.forms.attributes.Attribute;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.DataFormatValidator;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.messages.I18n;

import java.util.List;

/**
 * Частичная реализация атрибута списка строк.
 *
 * @param <D> Тип входных данных (DTO).
 * @param <E> Выходной тип данных (Entity).
 * @param <EV> Тип выходного значения атрибута.
 */
public abstract class TextListAttributeBase<D, E, EV>
    extends Attribute<D, List<String>, E, EV>
    implements ValueMapper<List<String>, EV> {

  public TextListAttributeBase(@Nonnull String titleKey, int maxSize, int maxItemLength) {
    this(
        TextListAttributeParams.Builder
            .<D, E, EV>create()
            .name(I18n.of(titleKey))
            .nonEmpty(false)
            .nullable(false)
            .maxSize(maxSize)
            .maxItemLength(maxItemLength)
            .build()
    );
  }

  public TextListAttributeBase(TextListAttributeParams<D, E, EV> params) {
    super(params);

    if (params.dataFormatValidator().isEmpty()) {
      params.setDataFormatValidator(new DefaultDataFormatValidator());
    }

    if (params.valueMapper().isEmpty()) {
      params.setValueMapper(this);
    }
  }

  private class DefaultDataFormatValidator implements DataFormatValidator<D, List<String>, E> {

    @Override
    @SuppressWarnings("unchecked")
    public <A extends AttributeParams<D, List<String>, E, ?>> void validate(List<String> value,
                                                                            @Nonnull A attrProps,
                                                                            @Nonnull FormContext<D, E> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      final var props = (TextListAttributeParams<D, E, EV>) attrProps;

      assertNotNullIfNonNullable(props, value);

      if (value == null) {
        return;
      }

      assertNotEmptyIfNonEmpty(props, value);
      assertSizeIfMaxSet(props, value);
      assertItemLengthIfMaxSet(props, value);
    }

    private void assertNotNullIfNonNullable(@Nonnull TextListAttributeParams<D, E, EV> props, List<String> value) {
      if (props.isNonNullable() && value == null) {
        throw new AttributeValueMissingException(props.getName());
      }
    }

    private void assertNotEmptyIfNonEmpty(@Nonnull TextListAttributeParams<D, E, EV> props, List<String> value) {
      if (props.isNonEmpty() && value.isEmpty()) {
        throw new AttributeValueMissingException(props.getName());
      }
    }

    private void assertSizeIfMaxSet(@Nonnull TextListAttributeParams<D, E, EV> props, List<String> value) {
      if (props.getMaxSize().isEmpty()) {
        return;
      }
      if (value.size() > props.getMaxSize().get()) {
        throw new AttributeBadFormatException(props.getName());
      }
    }

    private void assertItemLengthIfMaxSet(@Nonnull TextListAttributeParams<D, E, EV> props, List<String> value) {
      if (props.getMaxItemLength().isEmpty()) {
        return;
      }
      final var itemLengthExceeded = value.stream()
          .anyMatch(item -> item.length() >= props.getMaxItemLength().get());
      if (itemLengthExceeded) {
        throw new AttributeBadFormatException(props.getName());
      }
    }
  }
}
