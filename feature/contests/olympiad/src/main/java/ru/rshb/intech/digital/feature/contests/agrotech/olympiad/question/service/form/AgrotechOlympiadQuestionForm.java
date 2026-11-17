package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.service.form;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.censor.forms.CensorAnalyzer;
import ru.rshb.intech.digital.commons.forms.Form;
import ru.rshb.intech.digital.commons.forms.FormResultHandler;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheck;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckResult;
import ru.rshb.intech.digital.commons.forms.attributes.provider.FormAttributeProvider;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.AgrotechOlympiadQuestionDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadQuestion;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.service.AgrotechOlympiadService;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.service.notification.AgrotechOlympiadNotification;

import java.util.Collections;

/**
 * Форма подачи вопроса об олимпиаде "Навстречу Агротеху".
 */
@Component
public class AgrotechOlympiadQuestionForm extends Form<AgrotechOlympiadQuestionDto, AgrotechOlympiadQuestion> {

  public static final String TITLE_KEY = "agrotech.olympiad.question.form.name";

  private final AgrotechOlympiadService agrotechOlympiadService;
  private final AgrotechOlympiadNotification agrotechOlympiadNotification;

  public AgrotechOlympiadQuestionForm(@Nonnull FormAttributeProvider attributeProvider,
                                      @Nonnull CensorAnalyzer censorAnalyzer,
                                      @Nonnull AgrotechOlympiadService agrotechOlympiadService,
                                      @Nonnull AgrotechOlympiadNotification agrotechOlympiadNotification) {

    super(attributeProvider, TITLE_KEY, new PreCheck<>(censorAnalyzer));

    this.agrotechOlympiadService = agrotechOlympiadService;
    this.agrotechOlympiadNotification = agrotechOlympiadNotification;
  }

  @Nonnull
  @Override
  public AgrotechOlympiadQuestion process(@Nonnull AgrotechOlympiadQuestionDto inputData) {
    return process(
        inputData,
        AgrotechOlympiadQuestion::new,
        new ResultHandler(agrotechOlympiadService, agrotechOlympiadNotification)
    );
  }

  @RequiredArgsConstructor
  private static class ResultHandler implements FormResultHandler<AgrotechOlympiadQuestionDto, AgrotechOlympiadQuestion> {

    private final AgrotechOlympiadService agrotechOlympiadService;
    private final AgrotechOlympiadNotification agrotechOlympiadNotification;

    @Nonnull
    @Override
    public AgrotechOlympiadQuestion handleResult(@Nonnull AgrotechOlympiadQuestion question,
                                                 @Nonnull FormContext<AgrotechOlympiadQuestionDto, AgrotechOlympiadQuestion> context) {

      if (context.is(PreCheckResult.SUSPICIOUS)) {
        question.setSuspicious(true);
      }

      final var savedQuestion = agrotechOlympiadService.save(question);

      agrotechOlympiadNotification.send(savedQuestion, Collections.emptyList());

      return savedQuestion;
    }
  }
}
