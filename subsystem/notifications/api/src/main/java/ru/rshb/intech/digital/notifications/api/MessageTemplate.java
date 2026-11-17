package ru.rshb.intech.digital.notifications.api;

import jakarta.annotation.Nonnull;
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
public class MessageTemplate {  // TODO переделать в интерфейс

  private String templateKey;

  private String subject;

  private Values values;

  private List<Attachment> attachments;

  public MessageTemplate(@Nonnull String templateKey) {
    this.templateKey = templateKey;
  }

}
