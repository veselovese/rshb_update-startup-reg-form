package ru.rshb.intech.digital.commons.forms.attributes.email;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.DataFormatValidator;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheck;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeParams;

/**
 * Параметры атрибута "Электронная почта"
 *
 * @param <D> Тип входных данных (DTO).
 * @param <E> Выходной тип данных (Entity).
 */
public class EmailAttributeParams<D, E> extends TextAttributeParams<D, E> {

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Builder<D, E> {

    private final EmailAttributeParams<D, E> params = new EmailAttributeParams<>();

    @Nonnull
    public static <D, E> Builder<D, E> create() {
      return new Builder<>();
    }

    @Nonnull
    public Builder<D, E> name(@Nonnull String name) {
      this.params.setName(name);
      return this;
    }

    @Nonnull
    public Builder<D, E> nullable(boolean nullable) {
      this.params.setNullable(nullable);
      return this;
    }

    @Nonnull
    public Builder<D, E> dataFormatValidator(DataFormatValidator<D, String, E> dataFormatValidator) {
      this.params.setDataFormatValidator(dataFormatValidator);
      return this;
    }

    @Nonnull
    public Builder<D, E> preCheck(PreCheck<D, String, E> preCheck) {
      this.params.setPreCheck(preCheck);
      return this;
    }

    @Nonnull
    public Builder<D, E> valueMapper(ValueMapper<String, String> valueMapper) {
      this.params.setValueMapper(valueMapper);
      return this;
    }

    @Nonnull
    public Builder<D, E> businessRulesValidator(BusinessRulesValidator<D, E, String> businessRulesValidator) {
      this.params.setBusinessRulesValidator(businessRulesValidator);
      return this;
    }

    @Nonnull
    public Builder<D, E> maxLength(int maxLength) {
      this.params.setMaxLength(maxLength);
      return this;
    }

    @Nonnull
    public  EmailAttributeParams<D, E> build() {
      return params;
    }
  }

}
