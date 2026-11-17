package ru.rshb.intech.digital.feature.downloads.core.service.form.notification;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;
import ru.rshb.intech.digital.notifications.api.MessageTemplate;
import ru.rshb.intech.digital.notifications.entity.NotificationTemplate;
import ru.rshb.intech.digital.subsystem.content.files.entity.FileIndexEntry;

import java.util.Collections;
import java.util.List;

public class ExternalNotificationMessage extends MessageTemplate {

  private final FileIndexEntry fileEntry;
  private final NotificationTemplate notificationTemplate;

  public ExternalNotificationMessage(@Nonnull FileRequest fileRequest) {
    this.fileEntry = fileRequest.getFile();
    this.notificationTemplate = fileEntry.getExternalNotificationTemplate();
  }

  @Override
  public String getTemplateKey() {
    return notificationTemplate.getKey();
  }

  @Override
  public String getSubject() {
    return notificationTemplate.getDefaultSubject();
  }

  @Override
  public Values getValues() {
    return new Values()
        .put("downloadUrl", fileEntry.getUrl());
  }

  @Override
  public List<Attachment> getAttachments() {
    return Collections.emptyList();
  }
}
