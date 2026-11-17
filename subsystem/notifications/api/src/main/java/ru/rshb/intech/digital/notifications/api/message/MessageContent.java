package ru.rshb.intech.digital.notifications.api.message;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.http.MediaType;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;

import jakarta.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;


@Builder
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class MessageContent {

  private final String subject;
  private final byte[] data;
  private final MediaType mediaType;
  private final List<Attachment> attachments;


  public MessageContent(@Nonnull String subject,
                        @Nonnull byte[] data,
                        @Nonnull MediaType mediaType) {

    this.subject = subject;
    this.data = data;
    this.mediaType = mediaType;
    this.attachments = new ArrayList<>();
  }

  @Nonnull
  public List<Attachment> getAttachments() {
    return ListUtils.emptyIfNull(attachments);
  }
}
