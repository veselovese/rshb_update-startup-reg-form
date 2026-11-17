package ru.rshb.intech.digital.commons.forms.attributes;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;

/**
 * Атрибут формы.
 *
 * @param <D> Тип входных данных формы (DTO).
 * @param <DV> Тип входных данных атрибута.
 * @param <E> Выходной тип данных формы (Entity).
 * @param <EV> Тип выходных данных атрибута.
 */
@Slf4j
@RequiredArgsConstructor
public abstract class Attribute<D, DV, E, EV> {

  private final AttributeParams<D, DV, E, EV> properties;


  /**
   * Выполняет следующую обработку значения атрибута:
   * <ul>
   *   <li>Валидацию формата выходных данных.</li>
   *   <li>Предварительную проверку (цензура для текстовых атрибутов).</li>
   *   <li>Преобразование типа входных данных в выходной тип (маппинг)</li>
   *   <li>Валидацию выходных данных на соответствие бизнес-правилам</li>
   * </ul>
   *
   * @param context Контекст формы.
   */
  public void process(@Nonnull FormContext<D, E> context) {

    log.debug("Processing [{}] attribute...", properties.getName());

    // 1. Извлечь входное значение;
    final var inputValue = filterInputValue(
        supplyInputValue(context.getInputData())
    );

    // 2, Валидация формата входного значения
    validateDataFormat(inputValue, context);

    // 3. Предварительная проверка;
    if (doPreCheck(inputValue, context) == PreCheckResult.VIOLATION) {
      return;
    }

    // 4. Преобразование входного значения в адаптированное;
    final var mappedValue = mapValue(inputValue, context);

    // 5. Валидация адаптированного значения на соответствие бизнес-логике;
    validateBusinessRules(mappedValue, context);

    // 6. Запись адаптированного значения во внутреннюю структуру.
    consumeOutputValue(mappedValue, context.getOutputData());

    log.debug("[{}] attribute processed successfully", properties.getName());
  }

  @Nullable
  private DV filterInputValue(@Nullable DV inputValue) {
    return properties()
        .inputFilter()
        .orElse(input -> input)
        .apply(inputValue);
  }

  private void validateDataFormat(DV fieldValue, @Nonnull FormContext<D, E> context) {
    properties()
        .dataFormatValidator()
        .orElse(new BasicDataFormatValidator<>())
            .validate(fieldValue, properties, context);
    log.debug("Attribute [{}] data format validation: COMPLETE", properties().getName());
  }

  public static class BasicDataFormatValidator<D, DV, E> implements DataFormatValidator<D, DV, E> {

    @Override
    public <A extends AttributeParams<D, DV, E, ?>> void validate(DV value,
                                                                  @Nonnull A attrProps,
                                                                  @Nonnull FormContext<D, E> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      if (attrProps.isNonNullable() && value == null) {
        throw new AttributeValueMissingException(attrProps.getName());
      }
    }
  }

  @Nonnull
  private PreCheckResult doPreCheck(DV fieldValue, @Nonnull FormContext<D, E> context) {

    var preCheckResult = PreCheckResult.OK;

    if (processWithPreCheck()) {

      preCheckResult = properties()
          .preCheck()
          .orElse(new PreCheck.BypassedPreCheck<>())
          .process(fieldValue, context);

      if (preCheckResult != PreCheckResult.OK && context.is(PreCheckResult.OK)) {
        context.setPreCheckResult(preCheckResult);
      }

      if (preCheckResult == PreCheckResult.VIOLATION) {
        handlePreCheckViolation(context);
      }
    }

    log.debug("Attribute [{}] pre check: {}", properties().getName(), preCheckResult);
    return preCheckResult;
  }

  private void handlePreCheckViolation(@Nonnull FormContext<D, E> context) {
    log.warn("Form [{}] attribute [{}] pre check violation", context.getFormName(), properties().getName());
    context.setErrorAttributeName(properties().getName());
  }

  private EV mapValue(DV fieldValue, @Nonnull FormContext<D, E> context) {

    EV mappedValue = null;

    if (fieldValue != null) {
      mappedValue = properties.valueMapper()
          .orElseThrow(() -> new IllegalStateException("Value mapper not set for attribute: " + properties().getName()))
          .map(fieldValue, context);
    }

    log.debug("Attribute [{}] mapping: COMPLETE", properties().getName());

    return mappedValue;
  }

  private void validateBusinessRules(EV mappedValue, @Nonnull FormContext<D, E> context) {
    properties()
        .businessRulesValidator()
        .orElse((value, ctx) -> {})
        .validate(mappedValue, context);
    log.debug("Attribute [{}] business rules validation: COMPLETE", properties().getName());
  }

  @Nonnull
  public AttributeParams<D, DV, E, EV> properties() {
    return properties;
  }

  /**
   * Извлекает значение требуемого атрибута из входных данных.
   *
   * @param inputData Входные данные.
   * @return Значение атрибута.
   */
  public abstract DV supplyInputValue(D inputData);

  /**
   * Вносит полностью провалидированные данные, готовые к сохранению в БД в результирующую сущность.
   *
   * @param outputValue Значение для записи в сущность.
   * @param outputData Результирующая сущность.
   */
  public abstract void consumeOutputValue(EV outputValue, E outputData);

  /**
   * Возвращает порядок обработки атрибута по отношению к другим атрибутом отдельной формы.
   * Чем меньше номер - тем выше приоритет.
   *
   * @return Порядок обработки атрибута.
   */
  public int processOrder() {
    return Integer.MAX_VALUE;
  }

  /**
   * <p>Возвращает флаг включения предварительной проверки.</p>
   *
   * <p>Если <code>true</code> - то в общую процудуру обработки атрибута будет включена стадия предварительной
   * проверки.</p>
   *
   * <p>Предварительная проверка выполняется после валидации типа входных данных атрибута.</p>
   *
   * @return Если <code>true</code> - выполнить предварительную проверку.
   */
  public boolean processWithPreCheck() {
    return false;
  }
}
