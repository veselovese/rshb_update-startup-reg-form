package ru.rshb.intech.digital.feature.patents.core.service.publication.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.patents.core.dto.PatentPublicationDto;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.service.publication.form.PatentPublicationForm;

/**
 * Атрибут "Название проекта".
 * Форма заявки на размещение патента в витрине патентов.
 */
@Component("patentPubFormProjectName")
@FormAttribute(PatentPublicationForm.class)
public class ProjectName extends TextAttributeBase<PatentPublicationDto, PatentPublication> {

  public static final String TITLE_KEY = "patents.contest.registration.form.attribute.project.name";
  private static final int MAX_LENGTH = 255;

  public ProjectName() {
    super(TITLE_KEY, MAX_LENGTH);
  }

  @Override
  public String supplyInputValue(@Nonnull PatentPublicationDto inputData) {
    return inputData.getProjectName();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentPublication outputData) {
    outputData.setProjectName(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
