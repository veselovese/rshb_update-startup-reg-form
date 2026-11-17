package ru.rshb.intech.digital.notifications.api.message;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.rshb.intech.digital.notifications.api.message.processor.MessageCallback;

import jakarta.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
public class BasicMessage implements Message {

  private final String id;
  private final Set<String> channels;
  private final MessageContent content;

  @Setter(AccessLevel.PUBLIC)
  private Set<String> recipients;
  private final boolean collate;
  private MessageCallback beforeProcess;
  private MessageCallback afterProcess;


  @Nonnull
  public Optional<MessageCallback> getBeforeProcess() {
    return Optional.ofNullable(beforeProcess);
  }

  @Nonnull
  public Optional<MessageCallback> getAfterProcess() {
    return Optional.ofNullable(afterProcess);
  }

  @Nonnull
  @Override
  public Message clone() {
    return BasicMessage.builder()
        .id(id)
        .channels(new HashSet<>(channels))
        .content(
            MessageContent.builder()
                .subject(content.getSubject())
                .mediaType(content.getMediaType())
                .data(content.getData())
                .attachments(new ArrayList<>(content.getAttachments()))
                .build()
        )
        .recipients(new HashSet<>(recipients))
        .collate(collate)
        .beforeProcess(beforeProcess)
        .afterProcess(afterProcess)
        .build();
  }
}
