package ru.rshb.intech.digital.feature.contests.chef.service.contestant.form;

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
import ru.rshb.intech.digital.feature.contests.chef.dto.ChefContestantFormData;
import ru.rshb.intech.digital.feature.contests.chef.entity.ChefContestant;
import ru.rshb.intech.digital.feature.contests.chef.service.ChefContestService;
import ru.rshb.intech.digital.feature.contests.chef.service.contestant.notification.ChefContestantNotification;

import java.util.List;

/**
 * Форма участника конкурса "Цифровой повар".
 */
@Component
public class ChefContestantForm extends Form<ChefContestantFormData, ChefContestant> {

  public static final String TITLE_KEY = "contest.chef.participant.form";
  private static final String[] ALLOWED_EXTENSIONS = {"ppt", "pptx", "doc", "docx", "pdf"};

  private final ChefContestService chefContestService;
  private final ChefContestantNotification chefContestantNotification;

  public ChefContestantForm(@Nonnull FormAttributeProvider attributeProvider,
                            @Nonnull CensorAnalyzer censorAnalyzer,
                            @Nonnull ChefContestService chefContestService,
                            @Nonnull ChefContestantNotification chefContestantNotification) {

    super(attributeProvider, TITLE_KEY, new CensorPreCheck<>(censorAnalyzer));

    this.chefContestService = chefContestService;
    this.chefContestantNotification = chefContestantNotification;
  }

  @Nonnull
  @Override
  public ChefContestant process(@Nonnull ChefContestantFormData inputData) {

    final var attachments = Attachments.list()
        .ofNonNull(inputData.getAttachment(), ALLOWED_EXTENSIONS)
        .collectNonNull();

    return process(
        inputData,
        ChefContestant::new,
        new ResultHandler(chefContestService, chefContestantNotification, attachments)
    );
  }

  @RequiredArgsConstructor
  private static class ResultHandler implements FormResultHandler<ChefContestantFormData, ChefContestant> {

    private final ChefContestService chefContestService;
    private final ChefContestantNotification contestantNotification;
    private final List<Attachment> attachments;

    @Nonnull
    @Override
    public ChefContestant handleResult(@Nonnull ChefContestant contestant,
                                       @Nonnull FormContext<ChefContestantFormData, ChefContestant> context) {

      if (context.is(PreCheckResult.SUSPICIOUS)) {
        contestant.setSuspicious(true);
      }

      final var savedContestant = chefContestService.save(contestant);

      contestantNotification.send(savedContestant, attachments);

      return savedContestant;
    }
  }
}
