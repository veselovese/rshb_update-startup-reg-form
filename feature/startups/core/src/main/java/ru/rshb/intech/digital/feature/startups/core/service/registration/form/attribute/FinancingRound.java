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
import ru.rshb.intech.digital.feature.startups.entity.StartupFinancingRound;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;
import ru.rshb.intech.digital.messages.I18n;

/**
 * Атрибут "Раунд финансирования".
 * Форма заявки на включение стартапа в витрину.
 */
@Component("startupRegFormFinancingRound")
@FormAttribute(StartupRegistrationForm.class)
public class FinancingRound
    extends Attribute<StartupRegistrationDto, String, StartupRegistration, StartupFinancingRound> {

  public static final String TITLE_KEY = "startups.registration.attr.financing.round.name";


  public FinancingRound(@Nonnull StartupRegistrationLookupService lookupService) {
    super(
        AttributeParams.Builder
            .<StartupRegistrationDto, String, StartupRegistration, StartupFinancingRound>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(false)
            .dataFormatValidator(new FinancingDataFormatValidator(lookupService))
            .valueMapper(new FinancingValueMapper(lookupService))
            .build()
    );
  }

  @Override
  public String supplyInputValue(StartupRegistrationDto inputData) {
    return inputData.getFinancingId();
  }

  @Override
  public void consumeOutputValue(StartupFinancingRound outputValue, StartupRegistration outputData) {
    outputData.setFinancingRound(outputValue);
  }

  @RequiredArgsConstructor
  private static class FinancingDataFormatValidator
      extends BasicDataFormatValidator<StartupRegistrationDto, String, StartupRegistration> {

    private final StartupRegistrationLookupService lookupService;

    @Override
    public <A extends AttributeParams<StartupRegistrationDto, String, StartupRegistration, ?>>
      void validate(String value, @Nonnull A attrProps, @Nonnull FormContext<StartupRegistrationDto, StartupRegistration> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      super.validate(value, attrProps, context);

      if (!lookupService.isFinancingRoundExist(value)) {
        throw new AttributeBadFormatException(attrProps.getName());
      }
    }
  }

  @RequiredArgsConstructor
  private static class FinancingValueMapper implements ValueMapper<String, StartupFinancingRound> {

    private final StartupRegistrationLookupService lookupService;

    @Override
    public <D, E> StartupFinancingRound map(String externalId, @Nonnull FormContext<D, E> context) {
      return (externalId != null)
          ? lookupService.findFinancingRound(externalId)
              .orElseThrow(() -> new AttributeBadFormatException(I18n.of(FinancingRound.TITLE_KEY)))
          : null;
    }
  }
}
