package ru.rshb.intech.digital.commons.forms.attributes.phone;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import ru.rshb.intech.digital.commons.forms.attributes.Attribute;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.DataFormatValidator;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.commons.validation.phone.PhoneUtils;
import ru.rshb.intech.digital.messages.I18n;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Частичная реализация обработчика атрибута "Номер телефона".
 *
 * @param <D> Тип входных данных (DTO).
 * @param <E> Выходной тип данных (Entity).
 */
public abstract class PhoneAttributeBase<D, E> extends Attribute<D, String, E, String> {

  public static final String DEFAULT_TITLE_KEY = "common.form.attribute.phone.title";

  public PhoneAttributeBase() {
    this(new AttributeParams<>());
  }

  public PhoneAttributeBase(boolean nullable) {
    this(
        AttributeParams.Builder
            .<D, String, E, String>create()
            .nullable(nullable)
            .build()
    );
  }

  public PhoneAttributeBase(BusinessRulesValidator<D, E, String> businessRulesValidator) {
    this(
        AttributeParams.Builder
            .<D, String, E, String>create()
            .businessRulesValidator(businessRulesValidator)
            .build()
    );
  }

  public PhoneAttributeBase(boolean nullable, @Nonnull BusinessRulesValidator<D, E, String> businessRulesValidator) {
    this(
        AttributeParams.Builder
            .<D, String, E, String>create()
            .nullable(nullable)
            .businessRulesValidator(businessRulesValidator)
            .build()
    );
  }

  public PhoneAttributeBase(@Nonnull AttributeParams<D, String, E, String> params) {
    super(params);

    if (params.getName() == null) {
      params.setName(I18n.of(DEFAULT_TITLE_KEY));
    }

    if (params.dataFormatValidator().isEmpty()) {
      params.setDataFormatValidator(new PhoneDataFormatValidator<>());
    }

    if (params.valueMapper().isEmpty()) {
      params.setValueMapper(new PhoneValueMapper(params.isNullable()));
    }
  }

    private static class PhoneDataFormatValidator<D, E> implements DataFormatValidator<D, String, E> {

      @Override
      public <A extends AttributeParams<D, String, E, ?>> void validate(String value,
                                                                        @Nonnull A params,
                                                                        @Nonnull FormContext<D, E> context)
          throws AttributeValueMissingException, AttributeBadFormatException {

        if (params.isNullable() && isBlank(value)) {
          return;
        }

        if (params.isNonNullable() && isBlank(value)) {
          throw new AttributeValueMissingException(params.getName());
        }

        PhoneUtils.getPhone(value)
            .orElseThrow(() -> new AttributeBadFormatException(params.getName()));
      }
    }

  @RequiredArgsConstructor
  private static class PhoneValueMapper implements ValueMapper<String, String> {

    private static final String PHONE_SYMBOLS_REGEXP = "[^'+|^0-9]";

    private final boolean nullable;

    @Override
    public <D, E> String map(String value, @Nonnull FormContext<D, E> context) {

      if (nullable && isBlank(value)) {
        return null;
      }

      return value.trim()
          .replace(StringUtils.SPACE, StringUtils.EMPTY)
          .replaceAll(PHONE_SYMBOLS_REGEXP, StringUtils.EMPTY);
    }
  }
}
