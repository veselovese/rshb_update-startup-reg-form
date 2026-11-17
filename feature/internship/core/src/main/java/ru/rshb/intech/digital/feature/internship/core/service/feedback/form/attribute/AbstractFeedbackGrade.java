package ru.rshb.intech.digital.feature.internship.core.service.feedback.form.attribute;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.forms.attributes.number.IntegerAttributeBase;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFeedbackDto;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipFeedback;

public abstract class AbstractFeedbackGrade
    extends IntegerAttributeBase<InternshipFeedbackDto, InternshipFeedback> {

  private static final int MIN_GRADE_DEFAULT = 1;
  private static final int MAX_GRADE_DEFAULT = 5;

  public AbstractFeedbackGrade(@Nonnull String titleKey) {
    super(titleKey, MIN_GRADE_DEFAULT, MAX_GRADE_DEFAULT);
  }
}
