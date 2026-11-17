package ru.rshb.intech.digital.feature.downloads.core.service.form.notification;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.SIMPLE_DATE_TIME;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.notifications.api.NotificationMessage;

import jakarta.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class InternalNotificationMessage extends NotificationMessage {

  public static final String TOPIC_ID = "file.download.request";
  private static final String SUBJECT_TEMPLATE_KEY = "file.download.request.email.subject";
  private static final String INTERNAL_REPORT_TITLE_TEMPLATE_KEY = "file.download.report.title";

  private final FileRequest fileRequest;
  private final String requestedFileTitle;

  public InternalNotificationMessage(@Nonnull FileRequest fileRequest) {
    this.fileRequest = fileRequest;
    this.requestedFileTitle = fileRequest.getFile().getTitle();
  }

  @Override
  public String getTopic() {
    return TOPIC_ID;
  }

  @Override
  public String getSubject() {
    return I18n.of(
        SUBJECT_TEMPLATE_KEY,
        requestedFileTitle,
        fileRequest.getFullName(),
        SIMPLE_DATE_TIME.format(fileRequest.getSubmitDate())
    );
  }

  @Override
  public boolean isUseDefaultSubject() {
    return false;
  }

  @Getter
  @RequiredArgsConstructor
  public enum ReportAttributes {

    SOURCE_FORM("sourceForm"),
    FULL_NAME("fullName"),
    SUBMIT_DATE("submitDate"),
    EMAIL("email"),
    PHONE("phone"),
    ORGANIZATION("organization"),
    POSITION("position"),
    SUSPICIOUS("suspicious")
    ;

    private final String key;

    @Override
    public String toString() {
      return key;
    }

    @Nonnull
    public static Values mapValues(@Nonnull FileRequest fileRequest, @Nonnull String fileTitle) {
      return new Values()
          .put(SOURCE_FORM, I18n.of(INTERNAL_REPORT_TITLE_TEMPLATE_KEY, fileTitle))
          .put(FULL_NAME, fileRequest.getFullName())
          .put(SUBMIT_DATE, SIMPLE_DATE_TIME.format(fileRequest.getSubmitDate()))
          .put(EMAIL, fileRequest.getEmail())
          .put(PHONE, fileRequest.getPhone())
          .put(ORGANIZATION, fileRequest.getOrganization())
          .put(POSITION, fileRequest.getPosition())
          .put(SUSPICIOUS, fileRequest.getSuspicious())
          ;
    }
  }

  @Override
  public Values getValues() {
    return ReportAttributes.mapValues(fileRequest, requestedFileTitle);
  }

  @Override
  public List<Attachment> getAttachments() {
    return Collections.emptyList();
  }
}
