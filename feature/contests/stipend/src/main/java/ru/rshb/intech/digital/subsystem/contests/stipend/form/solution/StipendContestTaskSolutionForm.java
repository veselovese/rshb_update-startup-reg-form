package ru.rshb.intech.digital.subsystem.contests.stipend.form.solution;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.censor.forms.CensorAnalyzer;
import ru.rshb.intech.digital.censor.forms.CensorPreCheck;
import ru.rshb.intech.digital.commons.forms.Form;
import ru.rshb.intech.digital.commons.forms.FormResultHandler;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckResult;
import ru.rshb.intech.digital.commons.forms.attributes.provider.FormAttributeProvider;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.commons.utils.attachment.Attachments;
import ru.rshb.intech.digital.subsystem.contests.stipend.dto.StipendContestSolutionFormData;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendContestSolution;
import ru.rshb.intech.digital.subsystem.contests.stipend.exception.TaskNotFoundException;
import ru.rshb.intech.digital.subsystem.contests.stipend.notification.StipendSolutionNotification;
import ru.rshb.intech.digital.subsystem.contests.stipend.service.StipendContestSolutionService;

import java.util.List;

/**
 * Форма решения по стипендиальному конкурсу.
 */
@Component
public class StipendContestTaskSolutionForm extends Form<StipendContestSolutionFormData, StipendContestSolution> {

  private static final String TITLE_KEY = "contest.stipend.solution.form.name";

  private final StipendContestSolutionService solutionService;
  private final StipendSolutionNotification solutionNotification;

  public StipendContestTaskSolutionForm(@Nonnull FormAttributeProvider attributeProvider,
                                        @Nonnull CensorAnalyzer censorAnalyzer,
                                        @Nonnull StipendContestSolutionService solutionService,
                                        @Nonnull StipendSolutionNotification solutionNotification) {

    super(attributeProvider, TITLE_KEY, new CensorPreCheck<>(censorAnalyzer));

    this.solutionService = solutionService;
    this.solutionNotification = solutionNotification;
  }

  @Nonnull
  @Override
  public StipendContestSolution process(@Nonnull StipendContestSolutionFormData inputData) {

    if (!solutionService.isTaskExist(inputData.getTaskId())) {
      throw new TaskNotFoundException(inputData.getTaskId());
    }

    final var attachments = Attachments.list()
        .ofNonNull(inputData.getAttachment(), solutionService.getFormAttachmentAllowedExtensions())
        .collectNonNull();

    return process(
        inputData,
        StipendContestSolution::new,
        new ResultHandler(solutionService, solutionNotification, attachments)
    );
  }

  @RequiredArgsConstructor
  private static class ResultHandler
      implements FormResultHandler<StipendContestSolutionFormData, StipendContestSolution> {

    private final StipendContestSolutionService solutionService;
    private final StipendSolutionNotification solutionNotification;
    private final List<Attachment> attachments;

    @Nonnull
    @Override
    public StipendContestSolution handleResult(@Nonnull StipendContestSolution result,
                                               @Nonnull FormContext<StipendContestSolutionFormData, StipendContestSolution> context) {

      if (context.is(PreCheckResult.SUSPICIOUS)) {
        result.setSuspicious(true);
      }

      result.setTaskId(context.getInputData().getTaskId());
      final var savedSolution = solutionService.save(result);
      solutionNotification.send(result, attachments);

      return savedSolution;
    }
  }
}
