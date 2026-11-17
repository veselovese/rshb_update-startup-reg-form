package ru.rshb.intech.digital.commons.forms.attributes.text;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.DataFormatValidator;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheck;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;

import java.util.List;
import java.util.Optional;

/**
 * Параметры атрибута списка строк.
 *
 * @param <D> Тип входных данных (DTO).
 * @param <E> Выходной тип данных (Entity).
 * @param <EV> Тип выходного значения атрибута.
 */
@Getter
@Setter(AccessLevel.PRIVATE)
public class TextListAttributeParams<D, E, EV> extends AttributeParams<D, List<String>, E, EV> {

  private boolean nonEmpty;
  private Integer maxSize;
  private Integer maxItemLength;

  /**
   * Максимально допустимое кол-во строк.
   *
   * @return Optional со значением макс. кол-ва строк.
   */
  @Nonnull
  public Optional<Integer> getMaxSize() {
    return Optional.ofNullable(maxSize);
  }

  /**
   * Максимально допустимая длина строки.
   *
   * @return Optional со значением макс. длины строки.
   */
  @Nonnull
  public Optional<Integer> getMaxItemLength() {
    return Optional.ofNullable(maxItemLength);
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Builder<D, E, EV> {

    private final TextListAttributeParams<D, E, EV> params = new TextListAttributeParams<>();

    @Nonnull
    public static <D, E, EV> TextListAttributeParams.Builder<D, E, EV> create() {
      return new TextListAttributeParams.Builder<>();
    }

    /**
     * Задает максимальную длину списка строк.
     *
     * @param maxSize Максимальная длина списка строк.
     * @return Билдер параметров атрибута.
     */
    @Nonnull
    public TextListAttributeParams.Builder<D, E, EV> maxSize(int maxSize) {
      this.params.setMaxSize(maxSize);
      return this;
    }

    /**
     * Задает максимальную длину элемента списка строк (в символах).
     *
     * @param maxItemLength Макс. кол-во символов в каждой строке списка.
     * @return Билдер параметров атрибута.
     */
    @Nonnull
    public TextListAttributeParams.Builder<D, E, EV> maxItemLength(int maxItemLength) {
      this.params.setMaxItemLength(maxItemLength);
      return this;
    }

    @Nonnull
    public TextListAttributeParams.Builder<D, E, EV> name(@Nonnull String name) {
      this.params.setName(name);
      return this;
    }

    @Nonnull
    public TextListAttributeParams.Builder<D, E, EV> nullable(boolean nullable) {
      this.params.setNullable(nullable);
      return this;
    }

    @Nonnull
    public TextListAttributeParams.Builder<D, E, EV> nonEmpty(boolean nonEmpty) {
      this.params.setNonEmpty(nonEmpty);
      return this;
    }

    @Nonnull
    public TextListAttributeParams.Builder<D, E, EV> dataFormatValidator(DataFormatValidator<D, List<String>, E> dataFormatValidator) {
      this.params.setDataFormatValidator(dataFormatValidator);
      return this;
    }

    @Nonnull
    public TextListAttributeParams.Builder<D, E, EV> preCheck(PreCheck<D, List<String>, E> preCheck) {
      this.params.setPreCheck(preCheck);
      return this;
    }

    @Nonnull
    public TextListAttributeParams.Builder<D, E, EV> valueMapper(ValueMapper<List<String>, EV> valueMapper) {
      this.params.setValueMapper(valueMapper);
      return this;
    }

    @Nonnull
    public TextListAttributeParams.Builder<D, E, EV> businessRulesValidator(BusinessRulesValidator<D, E, EV> businessRulesValidator) {
      this.params.setBusinessRulesValidator(businessRulesValidator);
      return this;
    }

    @Nonnull
    public TextListAttributeParams<D, E, EV> build() {
      return params;
    }
  }
}
