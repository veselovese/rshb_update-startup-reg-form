package ru.rshb.intech.digital.feature.patents.core.service.publication.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.contests.patent.repository.ProjectStageRepository;
import ru.rshb.intech.digital.feature.patents.core.dto.PatentPublicationDto;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.service.publication.form.PatentPublicationForm;
import ru.rshb.intech.digital.messages.I18n;

/**
 * Атрибут "Стадия проекта".
 * Форма заявки на размещение патента в витрине патентов.
 */
@Component("patentPubFormProjectStage")
@FormAttribute(PatentPublicationForm.class)
public class ProjectStage extends TextAttributeBase<PatentPublicationDto, PatentPublication> {

  public static final String TITLE_KEY = "patents.contest.registration.form.attribute.project.stage";
  private static final int MAX_LENGTH = 100;

  public ProjectStage(@Nonnull ProjectStageRepository projectStageRepository) {
    super(TITLE_KEY, MAX_LENGTH, new ProjectStageValueMapper(projectStageRepository));
  }

  @Override
  public String supplyInputValue(@Nonnull PatentPublicationDto inputData) {
    return inputData.getProjectStageId();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentPublication outputData) {
    outputData.setProjectStage(outputValue);
  }

  @RequiredArgsConstructor
  private static class ProjectStageValueMapper implements ValueMapper<String, String> {

    private final ProjectStageRepository projectStageRepository;

    @Override
    public <D, E> String map(String rawValue, @Nonnull FormContext<D, E> context) {
      return projectStageRepository.findByExternalIdIgnoreCase(rawValue)
          .map(option -> option.getValue())
          .orElseThrow(() -> new AttributeBusinessRulesException(I18n.of(ProjectStage.TITLE_KEY)));
    }
  }
}
