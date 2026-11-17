package ru.rshb.intech.digital.commons.forms;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import ru.rshb.intech.digital.commons.forms.attributes.Attribute;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheck;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckResult;
import ru.rshb.intech.digital.commons.forms.attributes.provider.FormAttributeProvider;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValidationException;
import ru.rshb.intech.digital.commons.forms.exception.FormProcessingException;
import ru.rshb.intech.digital.messages.I18n;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static ru.rshb.intech.digital.commons.utils.Preconditions.checkState;

@Slf4j
public abstract class Form<D, E> {

  private final String titleKey;
  private final PreCheck<D, String, E> commonPreCheck;
  private final Map<String, Attribute<D, ?, E, ?>> attributeMap;


  public Form(@Nonnull String titleKey,
              @Nonnull PreCheck<D, String, E> preCheck) {

    this.titleKey = titleKey;
    this.commonPreCheck = preCheck;
    this.attributeMap = new LinkedHashMap<>();
  }

  public Form(@Nonnull FormAttributeProvider attributeProvider,
              @Nonnull String titleKey,
              @Nonnull PreCheck<D, String, E> preCheck) {

    this(titleKey, preCheck);

    setAttributeBeans(attributeProvider);

    log.debug("Form [{}] successfully initialized with [{}] attributes", getName(), attributeMap.size());
  }

  public Form(@Nonnull FormAttributeProvider attributeProvider,
              @Nonnull String titleKey) {

    this(attributeProvider, titleKey, new PreCheck.BypassedPreCheck<>());
  }


  @SuppressWarnings("unchecked")
  private void setAttributeBeans(@Nonnull FormAttributeProvider attributeProvider) {

    final List<Attribute<D, ?, E, ?>> attributes = attributeProvider.findByFormClass(getClass());

    for (final var attribute : attributes) {
      if (attribute.processWithPreCheck()) {
        addWithPreCheck((Attribute<D, String, E, ?>) attribute);
        continue;
      }
      add(attribute);
    }
  }


  @Nonnull
  public String getName() {
    return I18n.of(titleKey);
  }

  @SafeVarargs
  private void add(@Nonnull Attribute<D, ?, E, ?>... attributes) {
    Arrays.stream(attributes)
        .forEach(a -> attributeMap.put(a.properties().getName(), a));
  }

  @SafeVarargs
  private void addWithPreCheck(@Nonnull Attribute<D, String, E, ?>... attributes) {
    for (final var attribute : attributes) {
      final var props = attribute.properties();
      props.setPreCheck(commonPreCheck);
      attributeMap.put(props.getName(), attribute);
    }
  }

  @Nonnull
  public E process(@Nonnull D inputData,
                   @Nonnull Supplier<E> outputDataSupplier,
                   @Nonnull FormResultHandler<D, E> resultHandler) {

    checkState(!attributeMap.isEmpty(), "No form attributes set");

    final var formName = getName();
    log.debug("Processing [{}] form...", formName);

    final var outputData = outputDataSupplier.get();
    final var context = new FormContext<>(formName, inputData, outputData);


    for (final var attribute : attributeMap.values()) {

      attribute.process(context);

      if (context.getPreCheckResult() == PreCheckResult.VIOLATION) {
        log.warn("Form [{}] pre check violation: {} = {}",
                 formName,
                 context.getErrorAttributeName(),
                 attribute.supplyInputValue(inputData)
        );
        break;
      }
    }

    if (context.getPreCheckResult() == PreCheckResult.OK
        || context.getPreCheckResult() == PreCheckResult.SUSPICIOUS) {
      log.debug("Form [{}]: Handling result...", formName);
      return handleResult(resultHandler, context);
    }

    log.info("Form [{}]: processed", formName);
    return outputData;
  }

  @Nonnull
  public abstract E process(@Nonnull D inputData);

  @Nonnull
  private E handleResult(@Nonnull FormResultHandler<D, E> handler, @Nonnull FormContext<D, E> context) {
    try {
      return handler.handleResult(context.getOutputData(), context);
    } catch (AttributeValidationException e) {
      throw e;
    } catch (Exception e) {
      throw new FormProcessingException("Form result handling error", e);
    }
  }
}
