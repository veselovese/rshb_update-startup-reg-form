package ru.rshb.intech.digital.feature.patents.core.service.publication.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.feature.contests.patent.repository.FinancingRoundRepository;
import ru.rshb.intech.digital.feature.patents.core.dto.PatentPublicationDto;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.exception.PublicationValidationException;
import ru.rshb.intech.digital.feature.patents.core.service.publication.form.PatentPublicationForm;

/**
 * Атрибут "Раунд финансирования".
 * Форма заявки на размещение патента в витрине патентов.
 */
@Component("patentPubFormFinancingRound")
@FormAttribute(PatentPublicationForm.class)
public class FinancingRound extends TextAttributeBase<PatentPublicationDto, PatentPublication> {

  public static final String TITLE_KEY = "patents.contest.registration.form.attribute.fin.round";
  private static final int MAX_LENGTH = 100;

  public FinancingRound(@Nonnull FinancingRoundRepository financingRoundRepository) {
    super(TITLE_KEY, MAX_LENGTH, new FinancingRoundValueMapper(financingRoundRepository));
  }

  @Override
  public String supplyInputValue(@Nonnull PatentPublicationDto inputData) {
    return inputData.getFinancingRoundId();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentPublication outputData) {
    outputData.setFinancingRound(outputValue);
  }

  @RequiredArgsConstructor
  private static class FinancingRoundValueMapper implements ValueMapper<String, String> {

    private final FinancingRoundRepository financingRoundRepository;

    @Override
    public <D, E> String map(String rawValue, @Nonnull FormContext<D, E> context) {
      return financingRoundRepository.findByExternalIdIgnoreCase(rawValue)
          .map(option -> option.getValue())
          .orElseThrow(PublicationValidationException::invalidFinancingRound);
    }
  }
}
