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
import ru.rshb.intech.digital.feature.contests.patent.repository.FinancingRoundRepository;
import ru.rshb.intech.digital.feature.contests.patent.service.registration.form.PatentContestRegistrationForm;
import ru.rshb.intech.digital.messages.I18n;

/**
 * Атрибут "Раунд финансирования".
 * Форма регистрации участника в конкурсе патентов.
 */
@Component
@FormAttribute(PatentContestRegistrationForm.class)
public class FinancingRound extends TextAttributeBase<PatentContestRegistrationRequestDto, PatentContestRegistration> {

  private static final String TITLE_KEY = "patents.contest.registration.form.attribute.fin.round";
  private static final int MAX_LENGTH = 100;

  public FinancingRound(@Nonnull FinancingRoundRepository financingRoundRepository) {
    super(
        TextAttributeParams.Builder
            .<PatentContestRegistrationRequestDto, PatentContestRegistration>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(false)
            .maxLength(MAX_LENGTH)
            .valueMapper(new FinancingRoundValueMapper(financingRoundRepository))
            .build()
    );
  }

  @Override
  public String supplyInputValue(@Nonnull PatentContestRegistrationRequestDto inputData) {
    return inputData.getFinancingRoundId();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentContestRegistration outputData) {
    outputData.setFinancingRound(outputValue);
  }

  @RequiredArgsConstructor
  private static class FinancingRoundValueMapper implements ValueMapper<String, String> {

    private final FinancingRoundRepository financingRoundRepository;

    @Override
    public <D, E> String map(String rawValue, @Nonnull FormContext<D, E> context) {
      return financingRoundRepository.findByExternalIdIgnoreCase(rawValue)
          .map(option -> option.getValue())
          .orElseThrow(RegistrationValidationException::unknownFinancingRound);
    }
  }
}
