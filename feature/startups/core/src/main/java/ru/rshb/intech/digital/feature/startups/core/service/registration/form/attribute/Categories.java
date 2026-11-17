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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("startupRegFormCategories")
@FormAttribute(StartupRegistrationForm.class)
public class Categories extends Attribute<StartupRegistrationDto, List<String>, StartupRegistration, List<StartupCategory>> {

  public static final String TITLE_KEY = "startups.registration.attr.category.fixed,name";

  public Categories(@Nonnull StartupRegistrationLookupService lookupService) {
    super(
        AttributeParams.Builder
            .<StartupRegistrationDto, List<String>, StartupRegistration, List<StartupCategory>>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(false)
            .dataFormatValidator(new CategoriesFormatValidator(lookupService))
            .valueMapper(new CategoriesValueMapper(lookupService))
            .build()
    );
  }

  @Override
  public List<String> supplyInputValue(@Nonnull StartupRegistrationDto inputData) {
    return inputData.getCategoryIds();
  }

  @Override
  public void consumeOutputValue(List<StartupCategory> outputValue, @Nonnull StartupRegistration outputData) {
    outputData.setCategories(outputValue);
  }

  @RequiredArgsConstructor
  private static class CategoriesFormatValidator
      extends BasicDataFormatValidator<StartupRegistrationDto, List<String>, StartupRegistration> {

    private final StartupRegistrationLookupService lookupService;

    @Override
    public <A extends AttributeParams<StartupRegistrationDto, List<String>, StartupRegistration, ?>>
      void validate(List<String> externalIds,
                    @Nonnull A attrProps,
                    @Nonnull FormContext<StartupRegistrationDto, StartupRegistration> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      super.validate(externalIds, attrProps, context);

      if (externalIds == null || externalIds.isEmpty()
          || externalIds.stream().anyMatch(id -> id == null || !lookupService.isCategoryExist(id))) {
        throw new AttributeBadFormatException(attrProps.getName());
      }
    }
  }

  @RequiredArgsConstructor
  private static class CategoriesValueMapper implements ValueMapper<List<String>, List<StartupCategory>> {

    private final StartupRegistrationLookupService lookupService;

    @Override
    public <D, E> List<StartupCategory> map(List<String> externalIds, @Nonnull FormContext<D, E> context) {
      return externalIds.stream()
          .map(id -> lookupService.findCategory(id)
              .orElseThrow(() -> new AttributeBadFormatException(I18n.of(Categories.TITLE_KEY))))
          .collect(Collectors.toCollection(ArrayList::new));
    }
  }
}
