package ru.rshb.intech.digital.subsystem.contests.stipend.form.solution.attribute;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.email.EmailAttributeBase;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.subsystem.contests.stipend.dto.StipendContestSolutionFormData;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendContestSolution;
import ru.rshb.intech.digital.subsystem.contests.stipend.exception.SolutionValidationException;
import ru.rshb.intech.digital.subsystem.contests.stipend.form.solution.StipendContestTaskSolutionForm;
import ru.rshb.intech.digital.subsystem.contests.stipend.service.StipendContestSolutionService;

/**
 * Атрибут "Электронная почта".
 * Форма решения по стипендиальному конкурсу.
 */
@Component("stipendContestSolutionEmail")
@FormAttribute(StipendContestTaskSolutionForm.class)
public class Email extends EmailAttributeBase<StipendContestSolutionFormData, StipendContestSolution> {

  private static final int PROCESS_ORDER = 2;


  public Email(@Nonnull StipendContestSolutionService solutionService) {
    super(new EmailDuplicateValidator(solutionService));
  }


  @Override
  public String supplyInputValue(@Nonnull StipendContestSolutionFormData inputData) {
    return inputData.getEmail();
  }

  @Override
  public void consumeOutputValue(String outputValue, @Nonnull StipendContestSolution outputData) {
    outputData.setEmail(outputValue);
  }

  @Override
  public int processOrder() {
    return PROCESS_ORDER;
  }


  @RequiredArgsConstructor
  private static class EmailDuplicateValidator
      implements BusinessRulesValidator<StipendContestSolutionFormData, StipendContestSolution, String> {

    private final StipendContestSolutionService solutionService;

    @Override
    public void validate(String email,
                         @Nonnull FormContext<StipendContestSolutionFormData, StipendContestSolution> context)
        throws AttributeBusinessRulesException {

      final var taskId = context.getInputData().getTaskId();
      if (solutionService.isMaxSolutionsPerUserExceededByEmail(taskId, email)) {
        throw SolutionValidationException.maxSolutionsPerUserExceeded();
      }
    }
  }
}
