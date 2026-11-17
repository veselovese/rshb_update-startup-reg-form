package ru.rshb.intech.digital.feature.startups.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.Attribute;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.feature.startups.core.service.registration.StartupRegistrationLookupService;
import ru.rshb.intech.digital.feature.startups.core.service.registration.form.StartupRegistrationForm;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegistrationDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupCategory;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;
import ru.rshb.intech.digital.messages.I18n;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Атрибут "Категория (из списка)".
 * Форма заявки на включение стартапа в витрину.
 */
@Component("startupRegFormCategoryId")
@FormAttribute(StartupRegistrationForm.class)
public class CategoryId extends Attribute<StartupRegistrationDto, String, StartupRegistration, StartupCategory> {

  public static final String TITLE_KEY = "startups.registration.attr.category.fixed,name";
  private static final int PROCESS_ORDER = 1;

  public CategoryId(@Nonnull StartupRegistrationLookupService lookupService) {
    super(
        AttributeParams.Builder
            .<StartupRegistrationDto, String, StartupRegistration, StartupCategory>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(true)
            .dataFormatValidator(new CategoryFormatValidator(lookupService))
            .valueMapper(new CategoryValueMapper(lookupService))
            .build()
    );
  }

  @Override
  public String supplyInputValue(@Nonnull StartupRegistrationDto inputData) {
    return inputData.getCategoryId();
  }

  @Override
  public void consumeOutputValue(StartupCategory outputValue, @Nonnull StartupRegistration outputData) {
    outputData.setCategory(outputValue);
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }

  @RequiredArgsConstructor
  private static class CategoryFormatValidator
      extends BasicDataFormatValidator<StartupRegistrationDto, String, StartupRegistration> {

    private final StartupRegistrationLookupService lookupService;

    @Override
    public <A extends AttributeParams<StartupRegistrationDto, String, StartupRegistration, ?>>
      void validate(String externalId,
                    @Nonnull A attrProps,
                    @Nonnull FormContext<StartupRegistrationDto, StartupRegistration> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      super.validate(externalId, attrProps, context);

      if (isBlank(externalId)) {
        return;
      }

      if (!lookupService.isCategoryExist(externalId)) {
        throw new AttributeBadFormatException(attrProps.getName());
      }
    }
  }

  @RequiredArgsConstructor
  private static class CategoryValueMapper implements ValueMapper<String, StartupCategory> {

    private final StartupRegistrationLookupService lookupService;

    @Override
    public <D, E> StartupCategory map(String externalId, @Nonnull FormContext<D, E> context) {
      return isNotBlank(externalId)
          ? lookupService.findCategory(externalId)
            .orElseThrow(() -> new AttributeBadFormatException(I18n.of(CategoryId.TITLE_KEY)))
          : null;
    }
  }
}
