package ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.InternshipRegistrationForm;

/**
 * Атрибут "Опыт работы или стажировки".
 * Форма подачи заявки на стажировку.
 */
@Component("internshipRegistrationAboutExperience")
@FormAttribute(InternshipRegistrationForm.class)
public class AboutExperience extends TextAttributeBase<InternshipRegistrationDto, Registration> {

  public static final String TITLE_KEY = "internship.registration.form.field.experience";


  public AboutExperience() {
    super(TITLE_KEY, true, Registration.EXPERIENCE_MAX_LEN);
  }


  @Override
  public String supplyInputValue(@Nonnull InternshipRegistrationDto inputData) {
    return inputData.getAboutExperience();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull Registration outputData) {
    outputData.setAboutExperience(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
