package ru.rshb.intech.digital.feature.patents.core.service.publication.notification;

import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.notifications.api.MessageTemplate;

import java.util.Collections;
import java.util.List;

public class ApplicantNotificationMsg extends MessageTemplate {

  private static final String TEMPLATE_KEY = "patents.showcase.publication.applicant.notification";

  @Override
  public String getTemplateKey() {
    return TEMPLATE_KEY;
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
