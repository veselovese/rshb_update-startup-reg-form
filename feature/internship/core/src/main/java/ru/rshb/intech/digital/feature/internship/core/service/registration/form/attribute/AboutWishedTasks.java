package ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.InternshipRegistrationForm;

/**
 * Атрибут "Ваши ожидания от участия в программе стажировки".
 * Форма подачи заявки на стажировку.
 */
@Component("internshipRegistrationAboutWishedTasks")
@FormAttribute(InternshipRegistrationForm.class)
public class AboutWishedTasks extends TextAttributeBase<InternshipRegistrationDto, Registration> {

  public static final String TITLE_KEY = "internship.registration.form.field.tasks";

  public AboutWishedTasks() {
    super(TITLE_KEY, Registration.DESIRED_TASKS_MAX_LEN);
  }


  @Override
  public String supplyInputValue(@Nonnull InternshipRegistrationDto inputData) {
    return inputData.getAboutWishedTasks();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull Registration outputData) {
    outputData.setAboutWishedTasks(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
