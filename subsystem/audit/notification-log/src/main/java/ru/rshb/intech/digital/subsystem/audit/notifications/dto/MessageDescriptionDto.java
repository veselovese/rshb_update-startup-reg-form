package ru.rshb.intech.digital.subsystem.audit.notifications.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Generated;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDescriptionDto {

  private List<AttachmentDescriptionDto> attachments;

  private String stacktrace;

  public boolean isNoData() {
    return isEmpty(attachments) && isBlank(stacktrace);
  }
}
