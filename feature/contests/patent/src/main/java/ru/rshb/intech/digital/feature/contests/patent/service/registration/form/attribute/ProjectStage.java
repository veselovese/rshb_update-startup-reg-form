package ru.rshb.intech.digital.feature.contests.patent.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeParams;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.feature.contests.patent.dto.PatentContestRegistrationRequestDto;
import ru.rshb.intech.digital.feature.contests.patent.entity.PatentContestRegistration;
import ru.rshb.intech.digital.feature.contests.patent.exception.RegistrationValidationException;
import ru.rshb.intech.digital.feature.contests.patent.repository.ProjectStageRepository;
import ru.rshb.intech.digital.feature.contests.patent.service.registration.form.PatentContestRegistrationForm;
import ru.rshb.intech.digital.messages.I18n;

/**
 * Атрибут "Стадия проекта".
 * Форма регистрации участника в конкурсе патентов.
 */
@Component
@FormAttribute(PatentContestRegistrationForm.class)
public class ProjectStage extends TextAttributeBase<PatentContestRegistrationRequestDto, PatentContestRegistration> {

  private static final String TITLE_KEY = "patents.contest.registration.form.attribute.project.stage";
  private static final int MAX_LENGTH = 100;

  public ProjectStage(@Nonnull ProjectStageRepository projectStageRepository) {
    super(
        TextAttributeParams.Builder
            .<PatentContestRegistrationRequestDto, PatentContestRegistration>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(false)
            .maxLength(MAX_LENGTH)
            .valueMapper(new ProjectStageValueMapper(projectStageRepository))
            .build()
    );
  }

  @Override
  public String supplyInputValue(@Nonnull PatentContestRegistrationRequestDto inputData) {
    return inputData.getProjectStageId();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentContestRegistration outputData) {
    outputData.setProjectStage(outputValue);
  }

  @RequiredArgsConstructor
  private static class ProjectStageValueMapper implements ValueMapper<String, String> {

    private final ProjectStageRepository projectStageRepository;

    @Override
    public <D, E> String map(String rawValue, @Nonnull FormContext<D, E> context) {
      return projectStageRepository.findByExternalIdIgnoreCase(rawValue)
          .map(option -> option.getValue())
          .orElseThrow(RegistrationValidationException::unknownProjectStage);
    }
  }
}
