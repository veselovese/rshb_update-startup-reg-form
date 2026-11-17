package ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.InternshipRegistrationForm;

/**
 * Атрибут "Знания и навыки".
 * Форма подачи заявки на стажировку.
 */
@Component("internshipRegistrationAboutSkills")
@FormAttribute(InternshipRegistrationForm.class)
public class AboutSkills extends TextAttributeBase<InternshipRegistrationDto, Registration> {

  public static final String TITLE_KEY = "internship.registration.form.field.skills";


  public AboutSkills() {
    super(TITLE_KEY, Registration.SKILLS_MAX_LEN);
  }


  @Override
  public String supplyInputValue(@Nonnull InternshipRegistrationDto inputData) {
    return inputData.getAboutSkills();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull Registration outputData) {
    outputData.setAboutSkills(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
