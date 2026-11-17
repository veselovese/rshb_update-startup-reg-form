package ru.rshb.intech.digital.feature.downloads.core.service.form;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.commons.forms.FormResultHandler;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckResult;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.feature.downloads.dto.FileRequestDto;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;

@RequiredArgsConstructor
public abstract class FileRequestResultHandler implements FormResultHandler<FileRequestDto, FileRequest> {

  private final FileRequestSaver saver;
  private final FileRequestNotificator notificator;


  @Nonnull
  @Override
  public FileRequest handleResult(@Nonnull FileRequest request,
                                  @Nonnull FormContext<FileRequestDto, FileRequest> context) {

    if (context.is(PreCheckResult.SUSPICIOUS)) {
      request.setSuspicious(true);
    }

    final var savedRequest = saver.save(request);

    notificator.sendNotification(savedRequest);

    return savedRequest;
  }
}
