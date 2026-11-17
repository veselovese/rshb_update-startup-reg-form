package ru.rshb.intech.digital.notifications.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Generated
public class NotificationMessage { // TODO переделать в интерфейс

  private String topic;

  private String subject;

  private boolean useDefaultSubject;

  private Values values;

  private List<Attachment> attachments;
}
