package ru.rshb.intech.digital.feature.startups.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.Attribute;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.feature.startups.core.service.registration.form.StartupRegistrationForm;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegistrationDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;
import ru.rshb.intech.digital.messages.I18n;

@Component("startupRegFormDetails")
@FormAttribute(StartupRegistrationForm.class)
public class StartupRegistrationDetails
    extends Attribute<StartupRegistrationDto, StartupRegistrationDto, StartupRegistration, StartupRegistrationDto> {

  private static final String TITLE_KEY = "startups.registration.form.name";

  public StartupRegistrationDetails() {
    super(
        AttributeParams.Builder
            .<StartupRegistrationDto, StartupRegistrationDto, StartupRegistration, StartupRegistrationDto>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(false)
            .valueMapper(new DetailsValueMapper())
            .build()
    );
  }

  @Override
  public StartupRegistrationDto supplyInputValue(@Nonnull StartupRegistrationDto inputData) {
    return inputData;
  }

  @Override
  public void consumeOutputValue(StartupRegistrationDto inputData, @Nonnull StartupRegistration outputData) {
    outputData.setCity(inputData.getCity());
    outputData.setOrganization(inputData.getOrganization());
    outputData.setInn(inputData.getInn());
    outputData.setWebsiteUrl(inputData.getWebsiteUrl());
    outputData.setInvestorName(inputData.getInvestorName());
    outputData.setRevenueRange(inputData.getRevenueRange());
    outputData.setHasAiSolutions(inputData.getHasAiSolutions());
    outputData.setVideoUrl(inputData.getVideoUrl());
  }

  private static class DetailsValueMapper implements ValueMapper<StartupRegistrationDto, StartupRegistrationDto> {

    @Override
    public <D, E> StartupRegistrationDto map(StartupRegistrationDto inputData,
                                             @Nonnull FormContext<D, E> context) {
      return inputData;
    }
  }
}
