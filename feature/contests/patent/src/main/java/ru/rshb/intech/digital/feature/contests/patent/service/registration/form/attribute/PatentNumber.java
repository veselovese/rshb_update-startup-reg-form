package ru.rshb.intech.digital.feature.contests.patent.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeParams;
import ru.rshb.intech.digital.feature.contests.patent.dto.PatentContestRegistrationRequestDto;
import ru.rshb.intech.digital.feature.contests.patent.entity.PatentContestRegistration;
import ru.rshb.intech.digital.feature.contests.patent.service.registration.form.PatentContestRegistrationForm;
import ru.rshb.intech.digital.messages.I18n;

/**
 * Атрибут "Номер патента".
 * Форма регистрации участника в конкурсе патентов.
 */
@Component
@FormAttribute(PatentContestRegistrationForm.class)
public class PatentNumber extends TextAttributeBase<PatentContestRegistrationRequestDto, PatentContestRegistration> {

  private static final String TITLE_KEY = "patents.contest.registration.form.attribute.patent.number";
  private static final int MAX_LENGTH = 100;

  public PatentNumber() {
    super(
        TextAttributeParams.Builder
            .<PatentContestRegistrationRequestDto, PatentContestRegistration>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(false)
            .maxLength(MAX_LENGTH)
            .build()
    );
  }

  @Override
  public String supplyInputValue(@Nonnull PatentContestRegistrationRequestDto inputData) {
    return inputData.getPatentNumber();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentContestRegistration outputData) {
    outputData.setPatentNumber(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
