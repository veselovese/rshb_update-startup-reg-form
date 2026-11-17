package ru.rshb.intech.digital.feature.downloads.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.commons.validation.phone.Phone;

@Schema(name = "Заявка на скачивание файла")
@Data
@Generated
@NoArgsConstructor
public class FileRequestDto {

  @Schema(
      description = "ID файла",
      example = "agrobit-fishtech-presentation",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{file.download.request.validation.file-id.missing}")
  @Size(
      max = 255,
      message = "{file.download.request.validation.file-id.format}"
  )
  private String fileId;

  @Schema(
      description = "ФИО пользователя",
      example = "Иванов Иван Иванович",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{common.error.validation.full-name.missing}")
  @Size(
      max = 255,
      message = "{common.error.validation.full-name.max.length.exceeded}"
  )
  private String name;

  @Schema(
      description = "Контактный номер телефона",
      example = "+7 (999) 999-99-99",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{common.error.validation.phone.missing}")
  @Phone(message = "{common.error.validation.phone.format}")
  private String phone;

  @Schema(
      description = "Электронная почта",
      example = "test@test.te",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{common.error.validation.email.missing}")
  @Email(message = "{common.error.validation.email.format}")
  private String email;

  @Schema(
      description = "Организация",
      example = "ООО Рога и Копыты"
  )
  @Size(
      max = 255,
      message = "{common.error.validation.organization.max.length.exceeded}"
  )
  private String organization;

  @Schema(
      description = "Должность",
      example = "Генеральный директор"
  )
  @Size(
      max = 255,
      message = "{common.error.validation.position.max.length.exceeded}"
  )
  private String position;
}