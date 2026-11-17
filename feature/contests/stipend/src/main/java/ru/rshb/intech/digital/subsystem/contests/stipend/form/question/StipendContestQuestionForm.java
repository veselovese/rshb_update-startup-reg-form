package ru.rshb.intech.digital.subsystem.contests.stipend.form.question;

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
import ru.rshb.intech.digital.subsystem.contests.stipend.dto.StipendContestQuestionFormData;
import ru.rshb.intech.digital.subsystem.contests.stipend.entity.StipendContestQuestion;
import ru.rshb.intech.digital.subsystem.contests.stipend.notification.StipendQuestionNotification;
import ru.rshb.intech.digital.subsystem.contests.stipend.service.StipendContestQuestionService;

import java.util.Collections;

/**
 * Форма вопроса по стипендиальному конкурсу.
 */
@Component
public class StipendContestQuestionForm extends Form<StipendContestQuestionFormData, StipendContestQuestion> {

  public static final String TITLE_KEY = "contest.stipend.question.form.name";

  private final StipendContestQuestionService questionService;
  private final StipendQuestionNotification questionNotification;


  public StipendContestQuestionForm(@Nonnull FormAttributeProvider attributeProvider,
                                    @Nonnull CensorAnalyzer censorAnalyzer,
                                    @Nonnull StipendContestQuestionService questionService,
                                    @Nonnull StipendQuestionNotification questionNotification) {

    super(attributeProvider, TITLE_KEY, new CensorPreCheck<>(censorAnalyzer));

    this.questionService = questionService;
    this.questionNotification = questionNotification;
  }

  @Nonnull
  @Override
  public StipendContestQuestion process(@Nonnull StipendContestQuestionFormData inputData) {
    return process(
        inputData,
        StipendContestQuestion::new,
        new ResultHandler(questionService, questionNotification)
    );
  }

  @RequiredArgsConstructor
  private static class ResultHandler
      implements FormResultHandler<StipendContestQuestionFormData, StipendContestQuestion> {

    private final StipendContestQuestionService questionService;
    private final StipendQuestionNotification questionNotification;

    @Nonnull
    @Override
    public StipendContestQuestion handleResult(@Nonnull StipendContestQuestion result,
                                               @Nonnull FormContext<StipendContestQuestionFormData, StipendContestQuestion> context) {

      if (context.is(PreCheckResult.SUSPICIOUS)) {
        result.setSuspicious(true);
      }

      final var savedQuestion = questionService.save(result);
      questionNotification.send(savedQuestion, Collections.emptyList());

      return savedQuestion;
    }
  }
}
