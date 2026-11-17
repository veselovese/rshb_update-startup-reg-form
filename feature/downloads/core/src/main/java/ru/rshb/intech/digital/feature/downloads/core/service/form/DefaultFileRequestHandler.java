package ru.rshb.intech.digital.feature.downloads.core.service.form;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.downloads.core.service.FileDownloadService;

@Component
public class DefaultFileRequestHandler extends FileRequestResultHandler {

  public DefaultFileRequestHandler(@Nonnull FileDownloadService saver,
                                   @Nonnull DefaultFileRequestNotificator notificator) {
    super(saver, notificator);
  }
}