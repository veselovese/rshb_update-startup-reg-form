package ru.rshb.intech.digital.feature.startups.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeParams;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.feature.startups.core.service.registration.form.StartupRegistrationForm;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegistrationDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;
import ru.rshb.intech.digital.messages.I18n;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Атрибут "Категория".
 * Форма заявки на включение стартапа в витрину.
 */
@Component("startupRegFormCategoryCustom")
@FormAttribute(StartupRegistrationForm.class)
public class CategoryCustom extends TextAttributeBase<StartupRegistrationDto, StartupRegistration> {

  public static final String TITLE_KEY = "startups.registration.attr.category.name";
  private static final int MAX_LENGTH = 60;
  private static final int PROCESS_ORDER = 2;

  public CategoryCustom() {
    super(
        TextAttributeParams.Builder
            .<StartupRegistrationDto, StartupRegistration>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(true)
            .maxLength(MAX_LENGTH)
            .dataFormatValidator(new CustomValueDataFormatValidator())
            .valueMapper(new CustomValueMapper())
            .build()
    );
  }


  @Override
  public String supplyInputValue(@Nonnull StartupRegistrationDto inputData) {
    return inputData.getCategoryCustom();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull StartupRegistration outputData) {
    outputData.setCategoryCustom(outputValue);
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }

  private static class CustomValueDataFormatValidator
      extends TextAttributeBase.DefaultTextDataFormatValidator<StartupRegistrationDto, StartupRegistration> {

    @Override
    public <A extends AttributeParams<StartupRegistrationDto, String, StartupRegistration, ?>>
      void validate(String value,
                    @Nonnull A attrProps,
                    @Nonnull FormContext<StartupRegistrationDto, StartupRegistration> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      super.validate(value, attrProps, context);

      final var catIdProvided = (context.getOutputData().getCategory() != null);
      if (catIdProvided) {
        return;
      }

      if (isBlank(value)) {
        throw new AttributeBadFormatException(attrProps.getName());
      }
    }
  }

  private static class CustomValueMapper implements ValueMapper<String, String> {

    @Override
    public <D, E> String map(String rawValue, @Nonnull FormContext<D, E> context) {
      final var outputData = (StartupRegistration) context.getOutputData();
      return (outputData.getCategory() == null)
          ? rawValue
          : null;
    }
  }
}
