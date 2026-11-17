package ru.rshb.intech.digital.puzzles.core.crossword.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.transaction.annotation.Transactional;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageCallback;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageProcessorContext;
import ru.rshb.intech.digital.puzzles.core.crossword.repository.CrosswordParticipantRepository;
import ru.rshb.intech.digital.puzzles.entity.CrosswordParticipant;

public class CrosswordCallback {

  private static final String SUBMIT_STATUS_SUCCESS = "SUCCESS";
  private static final String CROSSWORD_PARTICIPANT_PROP = "crossword.participant";


  @RequiredArgsConstructor
  public static class Before implements MessageCallback {

    private final CrosswordParticipant crosswordParticipant;
    private final CrosswordParticipantRepository crosswordParticipantRepository;

    @Override
    @Transactional
    public void proceed(@Nonnull MessageProcessorContext context) {
      final var savedEntity = crosswordParticipantRepository.save(crosswordParticipant);
      context.getProps()
          .put(CROSSWORD_PARTICIPANT_PROP, savedEntity);
    }
  }

  @RequiredArgsConstructor
  public static class After implements MessageCallback {
    private final CrosswordParticipantRepository crosswordParticipantRepository;

    @Override
    public void proceed(@Nonnull MessageProcessorContext context) {

      final var status = (context.getException() == null)
          ? SUBMIT_STATUS_SUCCESS
          : ExceptionUtils.getStackTrace(context.getException());

      final var entity = context.<CrosswordParticipant>getProp(CROSSWORD_PARTICIPANT_PROP);
      entity.setLog(StringUtils.left(status, CrosswordParticipant.LOG_LENGTH));
      crosswordParticipantRepository.save(entity);
    }
  }
}
