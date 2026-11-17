package ru.rshb.intech.digital.feature.innovations.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Generated;
import org.springframework.web.multipart.MultipartFile;
import ru.rshb.intech.digital.commons.validation.phone.Phone;

@Schema(name = "Заявка о потребности в технологиях")
@Data
@Generated
public class InnovationWindowDemandFormDto {



  @Schema(
      description = "Название компании. Макс. 200 символов.",
      example = "ООО \"Рога и копыта\""
  )
  @NotBlank(message = "{innovations.demand.error.validation.company.missing}")
  @Size(max = 200, message = "{innovations.demand.error.validation.company.max}")
  private String company;

  @Schema(
      description = "Ссылка на сайт. Mакс. 500 символов",
      example = "https://rshbdigital.ru"
  )
  @NotBlank(message = "{innovations.demand.error.validation.site.missing}")
  @Size(max = 500, message = "{innovations.demand.error.validation.site.max}")
  private String site;

  @Schema(
      description = "Краткое описание запроса. Макс. 300 символов.",
      example = "Lorem ipsum"
  )
  @NotBlank(message = "{innovations.demand.error.validation.summary.missing}")
  @Size(max = 300, message = "{innovations.demand.error.validation.summary.max}")
  private String summary;

  @Schema(
      description = "Полное описание запроса. Макс. 1000 символов",
      example = "Lorem ipsum"
  )
  @NotBlank(message = "{innovations.demand.error.validation.description.missing}")
  @Size(max = 1000, message = "{innovations.demand.error.validation.description.max}")
  private String description;

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
}
