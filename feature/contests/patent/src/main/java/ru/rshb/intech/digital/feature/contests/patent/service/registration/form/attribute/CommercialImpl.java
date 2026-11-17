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
import ru.rshb.intech.digital.feature.contests.patent.repository.CommercialImplRepository;
import ru.rshb.intech.digital.feature.contests.patent.service.registration.form.PatentContestRegistrationForm;
import ru.rshb.intech.digital.messages.I18n;

/**
 * Атрибут "Коммерческая реализация".
 * Форма регистрации участника в конкурсе патентов.
 */
@Component
@FormAttribute(PatentContestRegistrationForm.class)
public class CommercialImpl extends TextAttributeBase<PatentContestRegistrationRequestDto, PatentContestRegistration> {

  private static final String TITLE_KEY = "patents.contest.registration.form.attribute.commercial.impl";
  private static final int MAX_LENGTH = 100;

  public CommercialImpl(@Nonnull CommercialImplRepository commercialImplRepository) {
    super(
        TextAttributeParams.Builder
            .<PatentContestRegistrationRequestDto, PatentContestRegistration>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(false)
            .maxLength(MAX_LENGTH)
            .valueMapper(new CommImplValueMapper(commercialImplRepository))
            .build()
    );
  }

  @Override
  public String supplyInputValue(@Nonnull PatentContestRegistrationRequestDto inputData) {
    return inputData.getCommercialImplId();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentContestRegistration outputData) {
    outputData.setCommercialImpl(outputValue);
  }

  @RequiredArgsConstructor
  private static class CommImplValueMapper implements ValueMapper<String, String> {

    private final CommercialImplRepository commercialImplRepository;

    @Override
    public <D, E> String map(String rawValue, @Nonnull FormContext<D, E> context) {
      return commercialImplRepository.findByExternalIdIgnoreCase(rawValue)
          .map(option -> option.getValue())
          .orElseThrow(RegistrationValidationException::unknownCommercialImplementation);
    }
  }
}
