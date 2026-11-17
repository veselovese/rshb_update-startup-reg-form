package ru.rshb.intech.digital.feedback.core.service.report.notification;

import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.notifications.api.MessageTemplate;
import ru.rshb.intech.digital.notifications.api.Templates;

import java.util.Collections;
import java.util.List;

public class ReporterNotificationMessage extends MessageTemplate {

  @Override
  public String getTemplateKey() {
    return Templates.DEFAULT_FORM_SUMBIT_RESPONSE;
  }

  @Override
  public String getSubject() {
    return null;
  }

  @Override
  public Values getValues() {
    return new Values();
  }

  @Override
  public List<Attachment> getAttachments() {
    return Collections.emptyList();
  }
}
