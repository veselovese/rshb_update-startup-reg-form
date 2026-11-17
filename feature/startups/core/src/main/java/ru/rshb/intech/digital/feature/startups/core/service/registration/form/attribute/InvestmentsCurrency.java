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
import ru.rshb.intech.digital.feature.startups.entity.StartupCurrency;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;
import ru.rshb.intech.digital.messages.I18n;

/**
 * Атрибут "Валюта инвестиций".
 * Форма заявки на включение стартапа в витрину.
 */
@Component("startupRegFormInvestCurrency")
@FormAttribute(StartupRegistrationForm.class)
public class InvestmentsCurrency
    extends Attribute<StartupRegistrationDto, String, StartupRegistration, StartupCurrency> {

  public static final String TITLE_KEY = "startups.registration.attr.investments.currency.name";


  public InvestmentsCurrency(@Nonnull StartupRegistrationLookupService lookupService) {
    super(
        AttributeParams.Builder
            .<StartupRegistrationDto, String, StartupRegistration, StartupCurrency>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(true)
            .dataFormatValidator(new CurrencyFormatValue(lookupService))
            .valueMapper(new CurrencyValueMapper(lookupService))
            .build()
    );
  }

  @Override
  public String supplyInputValue(StartupRegistrationDto inputData) {
    return inputData.getInvestmentsCurrencyId();
  }

  @Override
  public void consumeOutputValue(StartupCurrency outputValue, StartupRegistration outputData) {
    outputData.setInvestmentsCurrency(outputValue);
  }

  @RequiredArgsConstructor
  private static class CurrencyFormatValue
      extends BasicDataFormatValidator<StartupRegistrationDto, String, StartupRegistration> {

    private final StartupRegistrationLookupService lookupService;

    @Override
    public <A extends AttributeParams<StartupRegistrationDto, String, StartupRegistration, ?>>
      void validate(String externalId,
                    @Nonnull A attrProps,
                    @Nonnull FormContext<StartupRegistrationDto, StartupRegistration> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      super.validate(externalId, attrProps, context);

      if (context.getOutputData().getInvestmentsValue() == null) {
        return;
      }

      if (!lookupService.isCurrencyExist(externalId)) {
        throw new AttributeBadFormatException(attrProps.getName());
      }
    }
  }

  @RequiredArgsConstructor
  private static class CurrencyValueMapper implements ValueMapper<String, StartupCurrency> {

    private final StartupRegistrationLookupService lookupService;

    @Override
    public <D, E> StartupCurrency map(String externalId, @Nonnull FormContext<D, E> context) {
      return (externalId != null)
          ? lookupService.findCurrency(externalId)
            .orElseThrow(() -> new AttributeBadFormatException(I18n.of(InvestmentsCurrency.TITLE_KEY)))
          : null;
    }
  }
}
