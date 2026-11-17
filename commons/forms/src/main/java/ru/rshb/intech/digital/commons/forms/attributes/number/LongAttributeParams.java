package ru.rshb.intech.digital.commons.forms.attributes.number;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.DataFormatValidator;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;

import java.util.Optional;

/**
 * Параметры атрибута со значением типа Long.
 *
 * @param <D> Тип входных данных (DTO).
 * @param <E> Выходной тип данных (Entity).
 */
@Setter
@NoArgsConstructor
public class LongAttributeParams <D, E> extends AttributeParams<D, Long, E, Long> {

  private Long min;
  private Long max;

  @Nonnull
  public Optional<Long> min() {
    return Optional.ofNullable(min);
  }

  @Nonnull
  public Optional<Long> max() {
    return Optional.ofNullable(max);
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Builder<D, E> {

    private final LongAttributeParams<D, E> params = new LongAttributeParams<>();

    @Nonnull
    public static <D, E> LongAttributeParams.Builder<D, E> create() {
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
    public Builder<D, E> dataFormatValidator(DataFormatValidator<D, Long, E> dataFormatValidator) {
      this.params.setDataFormatValidator(dataFormatValidator);
      return this;
    }

    @Nonnull
    public Builder<D, E> valueMapper(ValueMapper<Long, Long> valueMapper) {
      this.params.setValueMapper(valueMapper);
      return this;
    }

    @Nonnull
    public Builder<D, E> businessRulesValidator(BusinessRulesValidator<D, E, Long> businessRulesValidator) {
      this.params.setBusinessRulesValidator(businessRulesValidator);
      return this;
    }

    @Nonnull
    public Builder<D, E> min(long minValue) {
      this.params.setMin(minValue);
      return this;
    }

    @Nonnull
    public Builder<D, E> max(long maxValue) {
      this.params.setMax(maxValue);
      return this;
    }

    @Nonnull
    public LongAttributeParams<D, E> build() {
      return params;
    }
  }
}
