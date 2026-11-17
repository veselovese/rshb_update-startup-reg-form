package ru.rshb.intech.digital.subsystem.contests.stipend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import ru.rshb.intech.digital.commons.validation.phone.Phone;

@Schema(name = "Данные формы с решением по задаче стипендиального конкурса")
@Getter
@Setter
@Generated
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class StipendContestSolutionFormData {

  @Schema(description = "ФИО участника.")
  @NotBlank(message = "{common.error.validation.full-name.missing}")
  private String contestantName;

  @Schema(description = "Контактный номер телефона участника.")
  @NotBlank(message = "{common.error.validation.phone.missing}")
  @Phone(message = "{common.error.validation.phone.format}")
  private String phone;

  @Schema(description = "Адрес электронной почты участника.")
  @NotBlank(message = "{common.error.validation.email.missing}")
  @Email(message = "{common.error.validation.email.format}")
  private String email;

  @Schema(description = "Комментарий.")
  @NotBlank(message = "{contest.stipend.solution.validation.error.comment.missing}")
  private String comment;

  @Schema(description = "Прикрепленный файл.")
  @NotNull(message = "{attachment.error.missing}")
  private MultipartFile attachment;

  @JsonIgnore
  private transient String taskId;
}
