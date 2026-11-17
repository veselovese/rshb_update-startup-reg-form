package ru.rshb.intech.digital.feature.patents.core.service.publication.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.feature.contests.patent.repository.ManagingSubjectRepository;
import ru.rshb.intech.digital.feature.patents.core.dto.PatentPublicationDto;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.exception.PublicationValidationException;
import ru.rshb.intech.digital.feature.patents.core.service.publication.form.PatentPublicationForm;

/**
 * Атрибут "Хозяйствующий субъект".
 * Форма заявки на размещение патента в витрине патентов.
 */
@Component("patentPubFormManagingSubject")
@FormAttribute(PatentPublicationForm.class)
public class ManagingSubject
    extends TextAttributeBase<PatentPublicationDto, PatentPublication> {

  public static final String TITLE_KEY = "patents.contest.registration.form.attribute.managing.subj";
  private static final int MAX_LENGTH = 100;

  public ManagingSubject(@Nonnull ManagingSubjectRepository managingSubjectRepository) {
    super(TITLE_KEY, MAX_LENGTH, new ManagingSubjectValueMapper(managingSubjectRepository));
  }

  @Override
  public String supplyInputValue(@Nonnull PatentPublicationDto inputData) {
    return inputData.getManagingSubjectId();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentPublication outputData) {
    outputData.setManagingSubject(outputValue);
  }

  @RequiredArgsConstructor
  private static class ManagingSubjectValueMapper implements ValueMapper<String, String> {

    private final ManagingSubjectRepository managingSubjectRepository;

    @Override
    public <D, E> String map(String rawValue, @Nonnull FormContext<D, E> context) {
      return managingSubjectRepository.findByExternalIdIgnoreCase(rawValue)
          .map(option -> option.getValue())
          .orElseThrow(PublicationValidationException::unknownManagingSubject);
    }
  }
}
