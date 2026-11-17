package ru.rshb.intech.digital.feature.contests.patent.service.registration.notification;

import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.MessageTemplate;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class ApplicantNotificationMessage extends MessageTemplate {

  private static final String TEMPLATE_KEY = "contest.patent.new.registration.applicant.notification";
  private static final String SUBJECT_KEY = "content.patent.new.registration.applicant.email";

  @Override
  public List<Attachment> getAttachments() {
    return Collections.emptyList();
  }

  @Override
  public String getSubject() {
    return I18n.of(SUBJECT_KEY);
  }

  @Override
  public String getTemplateKey() {
    return TEMPLATE_KEY;
  }

  @Override
  public Values getValues() {
    return new Values();
  }

}
