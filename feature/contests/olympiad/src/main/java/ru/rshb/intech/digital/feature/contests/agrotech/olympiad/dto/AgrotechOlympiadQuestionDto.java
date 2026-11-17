package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Generated;
import ru.rshb.intech.digital.commons.validation.phone.Phone;

@Schema(name = "Данные формы вопроса по олимпиаде среди школьников в агротехе")
@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgrotechOlympiadQuestionDto {

  @Schema(
      description = "ФИО",
      example = "Иванов Иван Иванович",
      maxLength = 255,
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{common.error.validation.full-name.missing}")
  @Size(
      max = 255,
      message = "{common.error.validation.full-name.max.length.exceeded}"
  )
  private String applicantName;

  @Schema(
      description = "Электронная почта",
      example = "test@test.te",
      maxLength = 254,
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{common.error.validation.email.missing}")
  @Email(message = "{common.error.validation.email.format}")
  private String email;

  @Schema(
      description = "Номер телефона",
      example = "+7 (999) 666-42-42",
      maxLength = 15,
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{common.error.validation.phone.missing}")
  @Phone(message = "{common.error.validation.phone.format}")
  private String phone;

  @Schema(
      description = "Текст вопроса",
      example = "Lorem ipsum dolor amit",
      maxLength = 1000,
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{agrotech.olympiad.question.validation.error.question.missing}")
  private String question;
}
