package ru.rshb.intech.digital.subsystem.audit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Generated;

import java.time.LocalDateTime;

@Data
@Generated
@Schema(name = "Запись в журнале аудита")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditLogEntryDto {

  @Schema(description = "Время события.")
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "dd.MM.yyyy HH:mm:ss"
  )
  private LocalDateTime timestamp;

  @Schema(description = "Инстанс сервера")
  private String instance;

  @Schema(description = "Наименование подсистемы, в которой возникло событие.")
  private String subsystem;

  @Schema(description = "Инициатор события.")
  private String initiator;

  @Schema(description = "Краткое описание события.")
  private String title;

  @Schema(description = "Расширенное описание события.")
  private String description;

  @Schema(description = "Метка \"Просмотрено\".")
  private Boolean viewed;

}
