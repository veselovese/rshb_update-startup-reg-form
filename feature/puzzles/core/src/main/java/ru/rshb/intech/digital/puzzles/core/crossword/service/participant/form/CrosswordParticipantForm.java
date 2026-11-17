package ru.rshb.intech.digital.puzzles.core.crossword.service.participant.form;

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
import ru.rshb.intech.digital.puzzles.core.crossword.service.CrosswordParticipantService;
import ru.rshb.intech.digital.puzzles.core.crossword.service.participant.notification.CrosswordParticipantNotification;
import ru.rshb.intech.digital.puzzles.dto.CrosswordParticipantDto;
import ru.rshb.intech.digital.puzzles.entity.CrosswordParticipant;

import java.util.List;

@Component
public class CrosswordParticipantForm extends Form<CrosswordParticipantDto, CrosswordParticipant> {

  public static final String TITLE_KEY = "puzzle.crossword.participant.form.name";
  private static final String[] ATTACHMENT_ALLOWED_EXTENSIONS = {"ppt", "pptx", "doc", "docx", "pdf"};

  private final CrosswordParticipantService participantService;
  private final CrosswordParticipantNotification notification;


  public CrosswordParticipantForm(@Nonnull FormAttributeProvider attributeProvider,
                                  @Nonnull CrosswordParticipantService participantService,
                                  @Nonnull CrosswordParticipantNotification notification,
                                  @Nonnull CensorAnalyzer censorAnalyzer) {

    super(attributeProvider, TITLE_KEY, new CensorPreCheck<>(censorAnalyzer));

    this.participantService = participantService;
    this.notification = notification;
  }

  @Nonnull
  @Override
  public CrosswordParticipant process(@Nonnull CrosswordParticipantDto inputData) {

    final var attachments = Attachments.list()
        .ofNonNull(inputData.getAttachData(), ATTACHMENT_ALLOWED_EXTENSIONS)
        .collectNonNull();

    return process(
        inputData,
        CrosswordParticipant::new,
        new ResultHandler(participantService, notification, attachments)
    );
  }

  @RequiredArgsConstructor
  private static class ResultHandler implements FormResultHandler<CrosswordParticipantDto, CrosswordParticipant> {

    private final CrosswordParticipantService participantService;
    private final CrosswordParticipantNotification participantNotification;
    private final List<Attachment> attachments;


    @Nonnull
    @Override
    public CrosswordParticipant handleResult(@Nonnull CrosswordParticipant result,
                                             @Nonnull FormContext<CrosswordParticipantDto, CrosswordParticipant> context) {

      if (context.is(PreCheckResult.SUSPICIOUS)) {
        result.setSuspicious(true);
      }

      final var savedParticipant = participantService.save(result);
      participantNotification.send(savedParticipant, attachments);

      return savedParticipant;
    }
  }
}
