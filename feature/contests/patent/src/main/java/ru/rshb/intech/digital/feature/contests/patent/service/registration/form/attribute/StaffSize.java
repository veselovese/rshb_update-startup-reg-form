package ru.rshb.intech.digital.feature.contests.patent.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.number.IntegerAttributeBase;
import ru.rshb.intech.digital.feature.contests.patent.dto.PatentContestRegistrationRequestDto;
import ru.rshb.intech.digital.feature.contests.patent.entity.PatentContestRegistration;
import ru.rshb.intech.digital.feature.contests.patent.service.registration.form.PatentContestRegistrationForm;

/**
 * Атрибут "Количество сотрудников".
 * Форма регистрации участника в конкурсе патентов.
 */
@Component
@FormAttribute(PatentContestRegistrationForm.class)
public class StaffSize extends IntegerAttributeBase<PatentContestRegistrationRequestDto, PatentContestRegistration> {

  public static final String TITLE_KEY = "patents.contest.registration.form.attribute.staff.size";

  public static final int MIN_VALUE = 1;
  public static final int MAX_VALUE = 999_999;

  public StaffSize() {
    super(TITLE_KEY, MIN_VALUE, MAX_VALUE, true);
  }


  @Override
  public Integer supplyInputValue(@Nonnull PatentContestRegistrationRequestDto inputData) {
    return inputData.getStaffSize();
  }

  @Override
  public void consumeOutputValue(Integer outputValue, @Nonnull PatentContestRegistration outputData) {
    outputData.setStaffSize(outputValue);
  }
}
