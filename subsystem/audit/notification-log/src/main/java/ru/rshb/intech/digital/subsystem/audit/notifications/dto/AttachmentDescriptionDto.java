package ru.rshb.intech.digital.subsystem.audit.notifications.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Generated;

@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachmentDescriptionDto {

  private String fileName;

  private String mimeType;

  private long size;
}
