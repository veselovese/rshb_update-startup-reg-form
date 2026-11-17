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
import ru.rshb.intech.digital.feature.references.locations.cache.entity.LocationCountry;
import ru.rshb.intech.digital.feature.references.locations.cache.service.LocationCachedService;
import ru.rshb.intech.digital.feature.startups.core.service.registration.form.StartupRegistrationForm;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegistrationDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;
import ru.rshb.intech.digital.messages.I18n;

/**
 * Атрибут "Страна".
 * Форма заявки на включение стартапа в витрину.
 */
@Component("startupRegFormCountry")
@FormAttribute(StartupRegistrationForm.class)
public class Country extends Attribute<StartupRegistrationDto, String, StartupRegistration, LocationCountry> {

  public static final String TITLE_KEY = "common.form.attribute.country.title";


  public Country(@Nonnull LocationCachedService locationService) {
    super(
        AttributeParams.Builder
            .<StartupRegistrationDto, String, StartupRegistration, LocationCountry>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(false)
            .dataFormatValidator(new CountryFormatValidator(locationService))
            .valueMapper(new CountryMapper(locationService))
            .build()
    );
  }

  @Override
  public String supplyInputValue(StartupRegistrationDto inputData) {
    return inputData.getCountryId();
  }

  @Override
  public void consumeOutputValue(LocationCountry outputValue, StartupRegistration outputData) {
    outputData.setCountry(outputValue);
  }

  @Override
  public int processOrder() {
    return 1;
  }

  @RequiredArgsConstructor
  private static class CountryFormatValidator
      extends BasicDataFormatValidator<StartupRegistrationDto, String, StartupRegistration> {

    private final LocationCachedService locationService;

    @Override
    public <A extends AttributeParams<StartupRegistrationDto, String, StartupRegistration, ?>>
      void validate(String countryId,
                    @Nonnull A attrProps,
                    @Nonnull FormContext<StartupRegistrationDto, StartupRegistration> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      super.validate(countryId, attrProps, context);

      if (!locationService.isCountryExist(countryId)) {
        throw new AttributeBadFormatException(attrProps.getName());
      }
    }
  }

  @RequiredArgsConstructor
  private static class CountryMapper implements ValueMapper<String, LocationCountry> {

    private final LocationCachedService locationService;

    @Override
    public <D, E> LocationCountry map(String countryId, @Nonnull FormContext<D, E> context) {
      return locationService.findCountryByExternalId(countryId)
          .orElseThrow(() -> new AttributeBadFormatException(I18n.of(Country.TITLE_KEY)));
    }
  }
}
