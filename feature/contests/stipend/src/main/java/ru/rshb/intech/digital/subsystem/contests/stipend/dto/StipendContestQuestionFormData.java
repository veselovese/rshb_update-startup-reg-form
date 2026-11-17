package ru.rshb.intech.digital.subsystem.contests.stipend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import ru.rshb.intech.digital.commons.validation.phone.Phone;

@Schema(name = "Данные формы вопроса по стипендиальному конкурсу")
@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
public class StipendContestQuestionFormData {

  @Schema(description = "ФИО задающего вопрос.")
  @NotBlank(message = "{common.error.validation.full-name.missing}")
  private String applicantName;

  @Schema(description = "Контактный номер телефона.")
  @NotBlank(message = "{common.error.validation.phone.missing}")
  @Phone(message = "{common.error.validation.phone.format}")
  private String phone;

  @Schema(description = "Адрес электронной почты.")
  @NotBlank(message = "{common.error.validation.email.missing}")
  @Email(message = "{common.error.validation.email.format}")
  private String email;

  @Schema(description = "Текст вопроса.")
  @NotBlank(message = "{contest.stipend.question.validation.error.question.missing}")
  private String question;
}
