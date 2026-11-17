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
 * Параметры атрибута со значением типа Integer.
 *
 * @param <D> Тип входных данных (DTO).
 * @param <E> Выходной тип данных (Entity).
 */
@Setter
@NoArgsConstructor
public class IntegerAttributeParams<D, E> extends AttributeParams<D, Integer, E, Integer> {

  private Integer min;
  private Integer max;

  /**
   * Минимальное допустимое значение атрибута.
   *
   * @return Optional мин. допустимым значением.
   */
  @Nonnull
  public Optional<Integer> min() {
    return Optional.ofNullable(min);
  }

  /**
   * Максимальное допустимое значение атрибута.
   *
   * @return Optional макс. допустимым значением.
   */
  @Nonnull
  public Optional<Integer> max() {
    return Optional.ofNullable(max);
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Builder<D, E> {

    private final IntegerAttributeParams<D, E> params = new IntegerAttributeParams<>();

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
    public Builder<D, E> dataFormatValidator(DataFormatValidator<D, Integer, E> dataFormatValidator) {
      this.params.setDataFormatValidator(dataFormatValidator);
      return this;
    }

    @Nonnull
    public Builder<D, E> valueMapper(ValueMapper<Integer, Integer> valueMapper) {
      this.params.setValueMapper(valueMapper);
      return this;
    }

    @Nonnull
    public Builder<D, E> businessRulesValidator(BusinessRulesValidator<D, E, Integer> businessRulesValidator) {
      this.params.setBusinessRulesValidator(businessRulesValidator);
      return this;
    }

    @Nonnull
    public Builder<D, E> min(int minValue) {
      this.params.setMin(minValue);
      return this;
    }

    @Nonnull
    public Builder<D, E> max(int maxValue) {
      this.params.setMax(maxValue);
      return this;
    }

    @Nonnull
    public  IntegerAttributeParams<D, E> build() {
      return params;
    }
  }
}
