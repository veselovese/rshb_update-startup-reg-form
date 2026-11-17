package ru.rshb.intech.digital.notifications.api;

import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.messages.I18n;

import java.util.Collections;
import java.util.List;

public class DefaultAnswerMessageTemplate extends MessageTemplate {

  private static final String DEFAULT_SUBJECT = I18n.of("default.applicant.response.email.subject");

  @Override
  public String getTemplateKey() {
    return Templates.DEFAULT_FORM_SUMBIT_RESPONSE;
  }

  @Override
  public String getSubject() {
    return DEFAULT_SUBJECT;
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
