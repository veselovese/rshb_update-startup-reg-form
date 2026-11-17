package ru.rshb.intech.digital.subsystem.audit.notifications.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.exception.ExceptionUtils;
import ru.rshb.intech.digital.commons.utils.attachment.Attachment;
import ru.rshb.intech.digital.notifications.api.message.Message;
import ru.rshb.intech.digital.subsystem.audit.notifications.dto.AttachmentDescriptionDto;
import ru.rshb.intech.digital.subsystem.audit.notifications.dto.MessageDescriptionDto;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.List;

public class MessageDescriptionFormatter {

  private static final ObjectMapper jsonMapper = JsonMapper.builder()
      .addModule(new JavaTimeModule())
      .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
      .serializationInclusion(JsonInclude.Include.NON_NULL)
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .configure(SerializationFeature.INDENT_OUTPUT, true)
      .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
      .build();

  @Nullable
  public String format(@Nonnull Message message, @Nullable Throwable throwable) {

    final var descriptionDto = new MessageDescriptionDto();

    if (throwable != null) {
      descriptionDto.setStacktrace(
          ExceptionUtils.getStackTrace(throwable)
      );
    }

    final var attachments = message.getContent().getAttachments();
    descriptionDto.setAttachments(
        attachmentDescriptions(attachments)
    );

    return doSerialize(descriptionDto);
  }

  @Nonnull
  private List<AttachmentDescriptionDto> attachmentDescriptions(@Nonnull List<Attachment> attachments) {
    return attachments.stream()
        .map(attachment -> attachmentDescription(attachment))
        .toList();
  }

  @Nonnull
  private AttachmentDescriptionDto attachmentDescription(@Nonnull Attachment attachment) {
    final var dto = new AttachmentDescriptionDto();
    dto.setFileName(attachment.getOriginalFilename());
    dto.setMimeType(attachment.getContentType());
    dto.setSize(attachment.getData().length);
    return dto;
  }

  @Nullable
  private String doSerialize(@Nonnull MessageDescriptionDto descriptionDto) {

    if (descriptionDto.isNoData()) {
      return null;
    }

    try {
      return jsonMapper.writeValueAsString(descriptionDto);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Notification message description serialization error", e);
    }
  }
}
