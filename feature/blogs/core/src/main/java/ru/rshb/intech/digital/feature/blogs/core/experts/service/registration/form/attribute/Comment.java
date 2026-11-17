package ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationFormData;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.ExpertRegistrationForm;

/**
 * Атрибут "Комментарий по экспертизе".
 * Форма регистрации "Стать экспертом".
 */
@Component("expertRegistrationFormComment")
@FormAttribute(ExpertRegistrationForm.class)
public class Comment extends TextAttributeBase<ExpertRegistrationFormData, ExpertRegistration> {

  public static final String TITLE_KEY = "expert.registration.form.attribute.comment";
  public static final int MAX_LENGTH = 1000;

  public Comment() {
    super(TITLE_KEY, false, MAX_LENGTH);
  }

  @Override
  public String supplyInputValue(@Nonnull ExpertRegistrationFormData inputData) {
    return inputData.getComment();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull ExpertRegistration outputData) {
    outputData.setComment(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
