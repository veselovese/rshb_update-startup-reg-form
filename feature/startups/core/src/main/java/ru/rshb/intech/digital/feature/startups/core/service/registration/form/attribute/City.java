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
import ru.rshb.intech.digital.feature.references.locations.cache.entity.LocationCity;
import ru.rshb.intech.digital.feature.references.locations.cache.service.LocationCachedService;
import ru.rshb.intech.digital.feature.startups.core.service.registration.form.StartupRegistrationForm;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegistrationDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;
import ru.rshb.intech.digital.messages.I18n;

/**
 * Атрибут "Город".
 * Форма заявки на включение стартапа в витрину.
 */
@Component("startupRegFormCity")
@FormAttribute(StartupRegistrationForm.class)
public class City extends Attribute<StartupRegistrationDto, String, StartupRegistration, LocationCity> {

  public static final String TITLE_KEY = "common.form.attribute.city.title";
  private static final int PROCESS_ORDER = 2;

  public City(@Nonnull LocationCachedService locationService) {
    super(
        AttributeParams.Builder
            .<StartupRegistrationDto, String, StartupRegistration, LocationCity>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(false)
            .dataFormatValidator(new CityFormatValidator(locationService))
            .valueMapper(new CityMapper(locationService))
            .build()
    );
  }

  @Override
  public String supplyInputValue(@Nonnull StartupRegistrationDto inputData) {
    return inputData.getCityId();
  }

  @Override
  public void consumeOutputValue(LocationCity outputValue, StartupRegistration outputData) {
    outputData.setCity(outputValue);
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }

  @RequiredArgsConstructor
  private static class CityFormatValidator
      extends BasicDataFormatValidator<StartupRegistrationDto, String, StartupRegistration> {

    private final LocationCachedService locationService;

    @Override
    public <A extends AttributeParams<StartupRegistrationDto, String, StartupRegistration, ?>>
      void validate(String cityId,
                    @Nonnull A attrProps,
                    @Nonnull FormContext<StartupRegistrationDto, StartupRegistration> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      super.validate(cityId, attrProps, context);

      final var countryId = context.getOutputData().getCountry().getExternalId();

      if (!locationService.isCityExist(countryId, cityId)) {
        throw new AttributeBadFormatException(attrProps.getName());
      }
    }
  }

  @RequiredArgsConstructor
  private static class CityMapper implements ValueMapper<String, LocationCity> {

    private final LocationCachedService locationService;

    @Override
    @SuppressWarnings("unchecked")
    public <D, E> LocationCity map(String cityId, @Nonnull FormContext<D, E> context) {
      final var startupFormContext = (FormContext<StartupRegistrationDto, StartupRegistration>) context;
      final var countryId = startupFormContext.getOutputData().getCountry().getExternalId();
      return locationService.findCityByExternalId(countryId, cityId)
          .orElseThrow(() -> new AttributeBadFormatException(I18n.of(City.TITLE_KEY)));
    }
  }
}
