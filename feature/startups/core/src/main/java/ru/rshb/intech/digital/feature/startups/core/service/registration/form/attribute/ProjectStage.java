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
import ru.rshb.intech.digital.feature.startups.entity.StartupProjectStage;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;
import ru.rshb.intech.digital.messages.I18n;

/**
 * Атрибут "Этап проекта".
 * Форма заявки на включение стартапа в витрину.
 */
@Component("startupRegFormProjectStage")
@FormAttribute(StartupRegistrationForm.class)
public class ProjectStage
    extends Attribute<StartupRegistrationDto, String, StartupRegistration, StartupProjectStage> {

  public static final String TITLE_KEY = "startups.registration.attr.project.stage.name";


  public ProjectStage(@Nonnull StartupRegistrationLookupService lookupService) {
    super(
        AttributeParams.Builder
            .<StartupRegistrationDto, String, StartupRegistration, StartupProjectStage>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(false)
            .dataFormatValidator(new ProjectStageFormatValidator(lookupService))
            .valueMapper(new ProjectStageValueMapper(lookupService))
            .build()
    );
  }

  @Override
  public String supplyInputValue(StartupRegistrationDto inputData) {
    return inputData.getProjectStageId();
  }

  @Override
  public void consumeOutputValue(StartupProjectStage outputValue, StartupRegistration outputData) {
    outputData.setProjectStage(outputValue);
  }

  @RequiredArgsConstructor
  private static class ProjectStageFormatValidator
      extends BasicDataFormatValidator<StartupRegistrationDto, String, StartupRegistration> {

    private final StartupRegistrationLookupService lookupService;

    @Override
    public <A extends AttributeParams<StartupRegistrationDto, String, StartupRegistration, ?>>
      void validate(String externalId,
                    @Nonnull A attrProps,
                    @Nonnull FormContext<StartupRegistrationDto, StartupRegistration> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      super.validate(externalId, attrProps, context);

      if (!lookupService.isProjectStageExist(externalId)) {
        throw new AttributeBadFormatException(attrProps.getName());
      }
    }
  }

  @RequiredArgsConstructor
  private static class ProjectStageValueMapper implements ValueMapper<String, StartupProjectStage> {

    private final StartupRegistrationLookupService lookupService;

    @Override
    public <D, E> StartupProjectStage map(String externalId, @Nonnull FormContext<D, E> context) {
      return (externalId != null)
          ? lookupService.findProjectStage(externalId)
            .orElseThrow(() -> new AttributeBadFormatException(I18n.of(ProjectStage.TITLE_KEY)))
          : null;
    }
  }
}
