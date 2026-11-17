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
 * Атрибут "Победа в конкурсах".
 * Форма регистрации участника в конкурсе патентов.
 */
@Component
@FormAttribute(PatentContestRegistrationForm.class)
public class VictoryCases extends TextAttributeBase<PatentContestRegistrationRequestDto, PatentContestRegistration> {

  private static final String TITLE_KEY = "patents.contest.registration.form.attribute.victory.cases";
  private static final int MAX_LENGTH = 255;

  public VictoryCases() {
    super(
        TextAttributeParams.Builder
            .<PatentContestRegistrationRequestDto, PatentContestRegistration>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(true)
            .maxLength(MAX_LENGTH)
            .build()
    );
  }

  @Override
  public String supplyInputValue(@Nonnull PatentContestRegistrationRequestDto inputData) {
    return inputData.getVictoriesInContests();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull PatentContestRegistration outputData) {
    outputData.setVictoryCases(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
