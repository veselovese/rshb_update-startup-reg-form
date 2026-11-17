package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.service.notification;

import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.notifications.api.MessageTemplate;

import java.util.Collections;
import java.util.List;

public class ApplicantNotificationMessage extends MessageTemplate {

  private static final String MSG_TEMPLATE_KEY = "contest.agrotech.olympiad.question.applicant.notification";


  @Override
  public String getTemplateKey() {
    return MSG_TEMPLATE_KEY;
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
