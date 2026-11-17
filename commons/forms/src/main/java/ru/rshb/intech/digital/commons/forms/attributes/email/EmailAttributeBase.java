package ru.rshb.intech.digital.commons.forms.attributes.email;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.forms.attributes.Attribute;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.DataFormatValidator;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.commons.validation.email.EmailUtils;
import ru.rshb.intech.digital.messages.I18n;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trim;

/**
 * Частичная реализация обработчика атрибута "Электронная почта".
 *
 * @param <D> Тип входных данных (DTO).
 * @param <E> Выходной тип данных (Entity).
 */
public abstract class EmailAttributeBase<D, E> extends Attribute<D, String, E, String> {

  public static final String DEFAULT_TITLE_KEY = "common.form.attribute.email.title";

  public EmailAttributeBase() {
    this(new EmailAttributeParams<>());
  }

  public EmailAttributeBase(@Nonnull EmailAttributeParams<D, E> params) {
    super(params);

    if (params.getName() == null) {
      params.setName(I18n.of(DEFAULT_TITLE_KEY));
    }

    if (params.dataFormatValidator().isEmpty()) {
      params.setDataFormatValidator(new EmailDataFormatValidator<>());
    }

    if (params.valueMapper().isEmpty()) {
      params.setValueMapper(new EmailMapper());
    }
  }

  /**
   * Конструктор обработчика атрибута "Электронная почта".
   *
   * @param nullable если <code>true</code>, то <code>null</code> - допустимое входное значение.
   */
  public EmailAttributeBase(boolean nullable) {
    this(
        EmailAttributeParams.Builder
            .<D, E>create()
            .nullable(nullable)
            .build()
    );
  }

  /**
   * Конструктор обработчика атрибута "Электронная почта".
   *
   * @param businessRulesValidator кастомный валидатор бизнес-правил.
   */
  public EmailAttributeBase(BusinessRulesValidator<D, E, String> businessRulesValidator) {
    this(
        EmailAttributeParams.Builder
            .<D, E>create()
            .businessRulesValidator(businessRulesValidator)
            .build()
    );
  }

  private static class EmailDataFormatValidator<D, E> implements DataFormatValidator<D, String, E> {

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

      if (EmailUtils.isNotValidAddress(value)) {
        throw new AttributeBadFormatException(params.getName());
      }
    }
  }

  private static class EmailMapper implements ValueMapper<String, String> {

    @Override
    public <D, E> String map(String rawValue, @Nonnull FormContext<D, E> context) {
      return trim(rawValue);
    }
  }
}
