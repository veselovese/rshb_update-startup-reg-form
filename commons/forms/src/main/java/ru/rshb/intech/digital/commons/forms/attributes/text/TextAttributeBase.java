package ru.rshb.intech.digital.commons.forms.attributes.text;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.commons.forms.attributes.Attribute;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.DataFormatValidator;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeMaxLengthExceededException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.commons.validation.text.TextValidationUtils;
import ru.rshb.intech.digital.messages.I18n;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.trim;

/**
 * Частичная реализация текстового атрибута.
 *
 * @param <D> Тип входных данных (DTO).
 * @param <E> Выходной тип данных (Entity).
 */
@Getter
public abstract class TextAttributeBase<D, E> extends Attribute<D, String, E, String> {


  public TextAttributeBase(@Nonnull TextAttributeParams<D, E> params) {
    super(params);

    if (params.dataFormatValidator().isEmpty()) {
      params.setDataFormatValidator(new DefaultTextDataFormatValidator<>());
    }

    if (params.valueMapper().isEmpty()) {
      params.setValueMapper(new TextValueMapper());
    }

    if (params.inputFilter().isEmpty()) {
      params.setInputFilter(TextValidationUtils::removeNonPrintableChars);
    }
  }

  public TextAttributeBase(@Nonnull String titleKey, int maxLength) {
    this(titleKey, false, maxLength);
  }

  public TextAttributeBase(@Nonnull String titleKey, boolean nullable, int maxLength) {
    this(
        TextAttributeParams.Builder
            .<D, E>create()
            .name(I18n.of(titleKey))
            .nullable(nullable)
            .maxLength(maxLength)
            .build()
    );
  }

  public TextAttributeBase(@Nonnull String titleKey,
                           boolean nullable,
                           int maxLength,
                           DataFormatValidator<D, String, E> dataFormatValidator) {

    this(
        TextAttributeParams.Builder
            .<D, E>create()
            .name(I18n.of(titleKey))
            .nullable(nullable)
            .maxLength(maxLength)
            .dataFormatValidator(dataFormatValidator)
            .build()
    );
  }

  public TextAttributeBase(String titleKey,
                           int maxLength,
                           @Nonnull BusinessRulesValidator<D, E, String> brValidator) {

    this(titleKey, false, maxLength, brValidator);
  }

  public TextAttributeBase(String titleKey,
                           boolean nullable,
                           int maxLength,
                           @Nonnull BusinessRulesValidator<D, E, String> brValidator) {
    this(
        TextAttributeParams.Builder
            .<D, E>create()
            .name(I18n.of(titleKey))
            .nullable(nullable)
            .maxLength(maxLength)
            .businessRulesValidator(brValidator)
            .build()
    );
  }

  public TextAttributeBase(String titleKey,
                           int maxLength,
                           @Nonnull ValueMapper<String, String> valueMapper) {
    this(
        TextAttributeParams.Builder
            .<D, E>create()
            .name(I18n.of(titleKey))
            .nullable(false)
            .maxLength(maxLength)
            .valueMapper(valueMapper)
            .build()
    );
  }

  public static class DefaultTextDataFormatValidator<D, E> implements DataFormatValidator<D, String, E> {

    @Override
    @SuppressWarnings("unchecked")
    public <A extends AttributeParams<D, String, E, ?>> void validate(String value,
                                                                      @Nonnull A attrProps,
                                                                      @Nonnull FormContext<D, E> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      final var params = (TextAttributeParams<D, E>) attrProps;
      final var attrName = params.getName();
      final var nullable = params.isNullable();
      final var maxLength = params.getMaxLength();

      if (!nullable) {
        assertNotBlank(attrName, value);
      }

      if (isNotBlank(value)) {
        assertLengthValid(attrName, value, maxLength);
      }
    }

    private void assertNotBlank(@Nonnull String attributeName, @Nonnull String value) {
      if (isBlank(value)) {
        throw new AttributeValueMissingException(attributeName);
      }
    }

    private void assertLengthValid(@Nonnull String attributeName, @Nonnull String value, int maxLength) {
      if (value.trim().length() > maxLength) {
        throw new AttributeMaxLengthExceededException(attributeName);
      }
    }
  }

  @RequiredArgsConstructor
  public static class TextValueMapper implements ValueMapper<String, String> {

    @Override
    public <D, E> String map(String rawValue, @Nonnull FormContext<D, E> context) {
      return isNotBlank(rawValue)
          ? trim(rawValue)
          : null;
    }
  }

  public static class ThroughValueMapper implements ValueMapper<String, String> {

    @Override
    public <D, E> String map(String rawValue, @Nonnull FormContext<D, E> context) {
      return rawValue;
    }
  }
}
