package ru.rshb.intech.digital.feature.innovations.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Generated;
import org.springframework.web.multipart.MultipartFile;
import ru.rshb.intech.digital.commons.validation.phone.Phone;

@Schema(name = "Данные заявки о предложении по инновациям")
@Data
@Generated
public class GeneralRequestDto {

  @Schema(
      description = "ФИО заявителя. (max. 300 символов).",
      example = "Владимиров Владимир Владимирович"
  )
  @NotBlank(message = "{common.error.validation.full-name.missing}")
  private String applicantName;

  @Schema(
      description = """
                    Контактный номер телефона заявителя. Допустимые символы: [+, -, пробел, (, )].
                    Приводится к виду: +79996664214..
                    """,
      example = "+79996664214"
  )
  @NotBlank(message = "{common.error.validation.phone.missing}")
  @Phone(message = "{common.error.validation.phone.format}")
  private String phone;

  @Schema(
      description = "Адрес электронной почты заявителя. (max. 320 символов. Валидация по маске)",
      example = "vladimirov@none.com"
  )
  @NotBlank(message = "{common.error.validation.email.missing}")
  @Email(message = "{common.error.validation.email.format}")
  private String email;

  @Schema(
      description = """
                    Текст предложения или запроса (max. 1000 символов. При превышении остальная часть отбрасывается)
                    """,
      example = "А я сейчас вам расскажу..."
  )
  @NotBlank(message = "{innovations.general.request.error.validation.proposal.missing}")
  private String proposal;

  @Schema(description = "Файл с описанием или презентацией. Не болеее 10 МБ.")
  private MultipartFile attachment;
}
