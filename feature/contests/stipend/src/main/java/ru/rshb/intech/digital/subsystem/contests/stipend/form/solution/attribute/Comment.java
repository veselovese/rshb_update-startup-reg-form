package ru.rshb.intech.digital.subsystem.contests.stipend.form.solution.attribute;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.subsystem.contests.stipend.dto.StipendContestSolutionFormData;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendContestSolution;
import ru.rshb.intech.digital.subsystem.contests.stipend.form.solution.StipendContestTaskSolutionForm;

/**
 * Атрибут "Комментарий".
 * Форма решения по стипендиальному конкурсу.
 */
@Component("stipendContestSolutionComment")
@FormAttribute(StipendContestTaskSolutionForm.class)
public class Comment extends TextAttributeBase<StipendContestSolutionFormData, StipendContestSolution> {

  private static final String TITLE_KEY = "contest.stipend.solution.form.attribute.comment";

  public Comment() {
    super(TITLE_KEY, StipendContestSolution.COMMENT_LENGTH);
  }

  @Override
  public String supplyInputValue(@Nonnull StipendContestSolutionFormData inputData) {
    return inputData.getComment();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull StipendContestSolution outputData) {
    outputData.setComment(outputValue);
  }

  @Override
  public boolean processWithPreCheck() {
    return true;
  }
}
