package ru.rshb.intech.digital.puzzles.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import ru.rshb.intech.digital.commons.validation.phone.Phone;

@Schema(
    name = "Данные формы кроссворда",
    description = "Данные формы со сведениями о пользователе, разагадавшем кроссворд."
)
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
public class CrosswordParticipantDto {

  @Schema(
      description = "ФИО заявителя.",
      example = "Иннокентьев Иннокентий Вальдемарович"
  )
  @NotBlank(message = "{puzzle.crossword.error.validation.applicant.name.missing}")
  private String applicantName;

  @Schema(
      description = "Контактный номер телефона.",
      example = "+79996664214"
  )
  @NotBlank(message = "{common.error.validation.phone.missing}")
  @Phone(message = "{common.error.validation.phone.format}")
  private String phone;

  @Schema(
      description = "Адрес электронной почты.",
      example = "innokentiev@none.com"
  )
  @NotBlank(message = "{common.error.validation.email.missing}")
  @Email(message = "{common.error.validation.email.format}")
  private String email;

  @Schema(
      description = "Комментарий/дополнительные сведения в свободной форме.",
      example = "Иннокентий хорош в кроссворды."
  )
  private String description;

  @Schema(description = "Файл с резюме. Не болеее 10 МБ.")
  @NotNull(message = "{attachment.error.missing}")
  private MultipartFile attachData;

  @JsonIgnore
  private transient String crosswordId;
}
