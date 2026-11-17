package ru.rshb.intech.digital.commons.forms.attributes;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;
import java.util.function.Function;

/**
 * Общие параметры атрибута.
 *
 * @param <D> Тип входных данных формы (DTO).
 * @param <DV> Тип входных данных атрибута.
 * @param <E> Выходной тип данных формы (Entity).
 * @param <EV> Тип выходных данных атрибута.
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttributeParams<D, DV, E, EV> {

  /**
   * Отображаемое наименование атрибута.
   */
  @Getter
  private String name;

  /**
   * Флаг, устанавливающий допустимость <code>null</code>-значений.
   */
  @Getter
  private boolean nullable = false;

  private DataFormatValidator<D, DV, E> dataFormatValidator;
  private PreCheck<D, DV, E> preCheck;
  private ValueMapper<DV, EV> valueMapper;
  private BusinessRulesValidator<D, E, EV> businessRulesValidator;
  private Function<DV, DV> inputFilter;

  /**
   * Возвращает <code>true</code> если в качестве значения атрибута допустимы только не-<code>null</code>
   * значения.
   *
   * @return <code>true</code> - если допустимы только не-<code>null</code> значения.
   */
  public boolean isNonNullable() {
    return !nullable;
  }

  /**
   * Возвращает предварительный фильтр входного значения атрибута.
   *
   * @return Фильтр входного значения атрибута.
   */
  @Nonnull
  public Optional<Function<DV, DV>> inputFilter() {
    return Optional.ofNullable(inputFilter);
  }

  /**
   * Возвращает валидатор типа входного значения атрибута.
   *
   * @return Валидатор типа входного значения.
   */
  @Nonnull
  public Optional<DataFormatValidator<D, DV, E>> dataFormatValidator() {
    return Optional.ofNullable(dataFormatValidator);
  }

  /**
   * Возвращает обработчик предварительной проверки входных данных атрибута.
   *
   * @return Предварительный обработчик входных данных атрибута.
   */
  @Nonnull
  public Optional<PreCheck<D, DV, E>> preCheck() {
    return Optional.ofNullable(preCheck);
  }

  /**
   * Маппер типа входного значения атрибута в выходное значение атрибута.
   *
   * @return Маппер типов входного и выходного значений атрибута.
   */
  @Nonnull
  public Optional<ValueMapper<DV, EV>> valueMapper() {
    return Optional.ofNullable(valueMapper);
  }

  /**
   * Валидатор соответствия значения атрибута бизнес-правилам (бизнес-логика).
   *
   * @return Валидатор соответствия бизнес-правилам.
   */
  @Nonnull
  public Optional<BusinessRulesValidator<D, E, EV>> businessRulesValidator() {
    return Optional.ofNullable(businessRulesValidator);
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Builder<D, DV, E, EV> {

    private final AttributeParams<D, DV, E, EV> params = new AttributeParams<>();

    @Nonnull
    public static <D, DV, E, EV> Builder<D, DV, E, EV> create() {
      return new Builder<>();
    }

    @Nonnull
    public Builder<D, DV, E, EV> name(@Nonnull String name) {
      this.params.setName(name);
      return this;
    }

    @Nonnull
    public Builder<D, DV, E, EV> nullable(boolean nullable) {
      this.params.setNullable(nullable);
      return this;
    }

    @Nonnull
    public Builder<D, DV, E, EV> dataFormatValidator(DataFormatValidator<D, DV, E> dataFormatValidator) {
      this.params.setDataFormatValidator(dataFormatValidator);
      return this;
    }

    @Nonnull
    public Builder<D, DV, E, EV> preCheck(PreCheck<D, DV, E> preCheck) {
      this.params.setPreCheck(preCheck);
      return this;
    }

    @Nonnull
    public Builder<D, DV, E, EV> valueMapper(ValueMapper<DV, EV> valueMapper) {
      this.params.setValueMapper(valueMapper);
      return this;
    }

    @Nonnull
    public Builder<D, DV, E, EV> businessRulesValidator(BusinessRulesValidator<D, E, EV> businessRulesValidator) {
      this.params.setBusinessRulesValidator(businessRulesValidator);
      return this;
    }

    @Nonnull
    public AttributeParams<D, DV, E, EV> build() {
      return params;
    }

  }
}
