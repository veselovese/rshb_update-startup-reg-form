package ru.rshb.intech.digital.vacancies.core.service.candidate.form;

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
import ru.rshb.intech.digital.vacancies.core.service.candidate.VacancyCandidateService;
import ru.rshb.intech.digital.vacancies.core.service.candidate.notification.VacancyCandidateNotification;
import ru.rshb.intech.digital.vacancies.entity.CandidateCv;
import ru.rshb.intech.digital.vacancies.model.CandidateCvDto;

import java.util.List;

@Component
public class VacancyCandidateForm extends Form<CandidateCvDto, CandidateCv> {

  public static final String TITLE_KEY = "vacancies.candidate.cv.form.name";
  
  private final VacancyCandidateService vacancyCandidateService;
  private final VacancyCandidateNotification notificationService;

  public VacancyCandidateForm(@Nonnull FormAttributeProvider attributeProvider,
                              @Nonnull CensorAnalyzer censorAnalyzer,
                              @Nonnull VacancyCandidateService vacancyCandidateService,
                              @Nonnull VacancyCandidateNotification notificationService) {

    super(attributeProvider, TITLE_KEY, new CensorPreCheck<>(censorAnalyzer));

    this.vacancyCandidateService = vacancyCandidateService;
    this.notificationService = notificationService;
  }

  @Nonnull
  @Override
  public CandidateCv process(@Nonnull CandidateCvDto inputData) {

    final var attachments = Attachments.list()
        .ofNonNull(inputData.getAttachmentResume(), vacancyCandidateService.getFormAttachmentAllowedExtensions())
        .collectNonNull();

    return process(
        inputData,
        CandidateCv::new,
        new ResultHandler(vacancyCandidateService, notificationService, attachments)
    );
  }

  @RequiredArgsConstructor
  private static class ResultHandler implements FormResultHandler<CandidateCvDto, CandidateCv> {

    private final VacancyCandidateService vacancyCandidateService;
    private final VacancyCandidateNotification notification;
    private final List<Attachment> attachments;

    @Nonnull
    @Override
    public CandidateCv handleResult(@Nonnull CandidateCv cv,
                                    @Nonnull FormContext<CandidateCvDto, CandidateCv> context) {

      if (context.is(PreCheckResult.SUSPICIOUS)) {
        cv.setSuspicious(true);
      }

      final var savedCv = vacancyCandidateService.save(cv);
      notification.send(savedCv, attachments);

      return savedCv;
    }
  }
}
