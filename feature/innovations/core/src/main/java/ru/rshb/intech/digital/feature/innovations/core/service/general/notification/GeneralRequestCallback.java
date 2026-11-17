package ru.rshb.intech.digital.feature.innovations.core.service.general.notification;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.transaction.annotation.Transactional;
import ru.rshb.intech.digital.feature.innovations.core.entity.GeneralRequest;
import ru.rshb.intech.digital.feature.innovations.core.repository.general.GeneralRequestRepository;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageCallback;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageProcessorContext;

public class GeneralRequestCallback {

  public static final String SUBMIT_STATUS_SUCCESS = "SUCCESS";
  public static final String INNOVATION_PROPOSAL_PROP = "innovation.proposal";


  @RequiredArgsConstructor
  public static class Before implements MessageCallback {

    private final GeneralRequest generalRequest;
    private final GeneralRequestRepository generalRequestRepository;


    @Override
    @Transactional
    public void proceed(@Nonnull MessageProcessorContext context) {
      final var savedEntity = generalRequestRepository.save(generalRequest);
      context.getProps()
          .put(INNOVATION_PROPOSAL_PROP, savedEntity);
    }
  }

  @RequiredArgsConstructor
  public static class After implements MessageCallback {

    private final GeneralRequestRepository generalRequestRepository;


    @Override
    @Transactional
    public void proceed(@Nonnull MessageProcessorContext context) {

      final var status = (context.getException() == null)
          ? SUBMIT_STATUS_SUCCESS
          : ExceptionUtils.getStackTrace(context.getException());

      final var entity = context.<GeneralRequest>getProp(INNOVATION_PROPOSAL_PROP);
      entity.setLog(StringUtils.left(status, GeneralRequest.LOG_LENGTH));
      generalRequestRepository.save(entity);
    }
  }
}
