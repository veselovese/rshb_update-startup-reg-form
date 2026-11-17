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
import ru.rshb.intech.digital.feature.startups.entity.StartupClientsNumberVariant;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;
import ru.rshb.intech.digital.messages.I18n;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Атрибут "Количество клиентов".
 * Форма заявки на включение стартапа в витрину.
 */
@Component("startupRegFormClientsNumber")
@FormAttribute(StartupRegistrationForm.class)
public class ClientsNumber
    extends Attribute<StartupRegistrationDto, String, StartupRegistration, StartupClientsNumberVariant> {

  public static final String TITLE_KEY = "startups.registration.attr.clients.number.name";

  public ClientsNumber(@Nonnull StartupRegistrationLookupService lookupService) {
    super(
        AttributeParams.Builder
            .<StartupRegistrationDto, String, StartupRegistration, StartupClientsNumberVariant>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(false)
            .dataFormatValidator(new ClientsNumberFormatValidator(lookupService))
            .valueMapper(new ClientsNumberValueMapper(lookupService))
            .build()
    );
  }

  @Override
  public String supplyInputValue(StartupRegistrationDto inputData) {
    return inputData.getClientsId();
  }

  @Override
  public void consumeOutputValue(StartupClientsNumberVariant outputValue, StartupRegistration outputData) {
    outputData.setClientsNumberVariant(outputValue);
  }

  @RequiredArgsConstructor
  private static class ClientsNumberFormatValidator
      extends BasicDataFormatValidator<StartupRegistrationDto, String, StartupRegistration> {

    private final StartupRegistrationLookupService lookupService;

    @Override
    public <A extends AttributeParams<StartupRegistrationDto, String, StartupRegistration, ?>>
      void validate(String clientNumberVariantId,
                    @Nonnull A attrProps,
                    @Nonnull FormContext<StartupRegistrationDto, StartupRegistration> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      super.validate(clientNumberVariantId, attrProps, context);

      if (!lookupService.isClientsNumberVariantExist(clientNumberVariantId)) {
        throw new AttributeBadFormatException(attrProps.getName());
      }
    }
  }

  @RequiredArgsConstructor
  private static class ClientsNumberValueMapper implements ValueMapper<String, StartupClientsNumberVariant> {

    private final StartupRegistrationLookupService lookupService;

    @Override
    public <D, E> StartupClientsNumberVariant map(String externalId, @Nonnull FormContext<D, E> context) {
      return (isNotBlank(externalId))
          ? lookupService.findClientsNumberVariant(externalId)
            .orElseThrow(() -> new AttributeBadFormatException(I18n.of(ClientsNumber.TITLE_KEY)))
          : null;
    }
  }
}
