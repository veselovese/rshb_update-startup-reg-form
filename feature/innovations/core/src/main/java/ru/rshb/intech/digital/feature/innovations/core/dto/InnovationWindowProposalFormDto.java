package ru.rshb.intech.digital.feature.innovations.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Generated;
import org.springframework.web.multipart.MultipartFile;
import ru.rshb.intech.digital.commons.validation.phone.Phone;

@Schema(name = "Предложение с решенеим потребности в технологиях")
@Data
@Generated
public class InnovationWindowProposalFormDto {

  @Schema(
      description = "Описание предлагаемого решения. Макс. 1000 символов",
      example = "Lorem ipsum"
  )
  @NotBlank(message = "{innovations.solution.error.validation.description.missing}")
  @Size(
      max = 1000,
      message = "{innovations.solution.error.validation.description.max}"
  )
  private String description;

  @Schema(
      description = "Ссылка на стартап в витрине стартапов. Макс. 500 символов.",
      example = "https://rshbdigital.ru"
  )
  @Size(
      max = 500,
      message = "{innovations.solution.error.startup.link.max}"
  )
  private String startupLink;

  @Schema(
      description = "Наименование стартапа. Макс. 200 символов.",
      example = "Рога и копыта"
  )
  @NotBlank(message = "{innovations.solution.error.startup.name.missing}")
  @Size(
      max = 200,
      message = "{innovations.solution.error.startup.name.max}"
  )
  private String startupName;

  @Schema(
      description = "ФИО представителя",
      example = "Смит Джон Генриевич"
  )
  @NotBlank(message = "{innovations.solution.error.representative.name.missing}")
  @Size(
      max = 200,
      message = "{innovations.solution.error.representative.name.max}"
  )
  private String representativeName;

  @Schema(
      description = """
                    Контактный номер телефона заявителя. Допустимые символы: [+, -, пробел, (, )].
                    Приводится к виду: +79996664214.
                    """,
      example = "+7 (999) 666-42-42"
  )
  @NotBlank(message = "{common.error.validation.phone.missing}")
  @Phone(message = "{common.error.validation.phone.format}")
  private String phone;

  @Schema(
      description = "Адрес электронной почты заявителя. (max. 254 символов. Валидация по маске)",
      example = "vladimirov@none.com"
  )
  @NotBlank(message = "{common.error.validation.email.missing}")
  @Email(message = "{common.error.validation.email.format}")
  private String email;

  @Schema(description = "Файл с описанием или презентацией. Не болеее 20 МБ.")
  @NotNull(message = "{attachment.error.missing}")
  private MultipartFile attachment;

  @JsonIgnore
  private transient String demandCardId;
}
