package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.notification;

import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.MessageTemplate;

import java.util.Collections;
import java.util.List;

public class ApplicantNotificationMessage extends MessageTemplate {

  private static final String TEMPLATE_KEY = "contest.agrotech.olympiad.registration.applicant.notification";
  private static final String SUBJECT_KEY = "agrotech.olympiad.registration.applicant.notification.email.subject";


  @Override
  public String getTemplateKey() {
    return TEMPLATE_KEY;
  }

  @Override
  public String getSubject() {
    return I18n.of(SUBJECT_KEY);
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
