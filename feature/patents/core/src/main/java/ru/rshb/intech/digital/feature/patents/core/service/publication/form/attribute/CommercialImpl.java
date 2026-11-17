package ru.rshb.intech.digital.feature.patents.core.service.publication.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.feature.contests.patent.repository.CommercialImplRepository;
import ru.rshb.intech.digital.feature.patents.core.dto.PatentPublicationDto;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.exception.PublicationValidationException;
import ru.rshb.intech.digital.feature.patents.core.service.publication.form.PatentPublicationForm;

/**
 * Атрибут "Коммерческая реализация".
 * Форма заявки на размещение патента в витрине патентов.
 */
@Component("patentPubFormCommercialImpl")
@FormAttribute(PatentPublicationForm.class)
public class CommercialImpl extends TextAttributeBase<PatentPublicationDto, PatentPublication> {

  public static final String TITLE_KEY = "patents.contest.registration.form.attribute.commercial.impl";
  private static final int MAX_LENGTH = 100;

  public CommercialImpl(@Nonnull CommercialImplRepository commercialImplRepository) {
    super(TITLE_KEY, MAX_LENGTH, new CommImplValueMapper(commercialImplRepository));
  }

  @Override
  public String supplyInputValue(@Nonnull PatentPublicationDto inputData) {
    return inputData.getCommercialImplId();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentPublication outputData) {
    outputData.setCommercialImpl(outputValue);
  }

  @RequiredArgsConstructor
  private static class CommImplValueMapper implements ValueMapper<String, String> {

    private final CommercialImplRepository commercialImplRepository;

    @Override
    public <D, E> String map(String rawValue, @Nonnull FormContext<D, E> context) {
      return commercialImplRepository.findByExternalIdIgnoreCase(rawValue)
          .map(option -> option.getValue())
          .orElseThrow(PublicationValidationException::invalidCommercialImplementation);
    }
  }
}
