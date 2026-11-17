package ru.rshb.intech.digital.feature.blogs.core.experts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.rshb.intech.digital.commons.validation.phone.Phone;
import ru.rshb.intech.digital.commons.validation.url.Url;

import java.util.List;

@Schema(name = "Данные формы заявки \"Стать экспертом\"")
@Data
@Generated
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExpertRegistrationFormData {

  @Schema(
      description = "ФИО эксперта блогера",
      example = "Иванов Иван Иванович",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{common.error.validation.full-name.missing}")
  @Size(
      max = 255,
      message = "{common.error.validation.full-name.max.length.exceeded}"
  )
  private String expertName;

  @Schema(
      description = "Ссылка на профиль социальной сети в контакте",
      example = "https://vk.com/id123456789"
  )
  @Url(message = "{common.error.validation.vk.url.bad.format}")
  private String vkProfileUrl;

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
      description = """
                    Экспертные области блогера. Значения из справочника (и/или из поля "другое").
                    """,
      requiredMode = Schema.RequiredMode.REQUIRED,
      minLength = 1,
      maxLength = 3
  )
  @NotEmpty(message = "{expert.registration.form.validation.error.applicant.field.missing}")
  @Size(
      min = 1,
      max = 3,
      message = "{expert.registration.form.validation.error.applicant.field.size.exceeded}"
  )
  private List<String> directions;

  @Schema(description = "Ссылки на видео или аудио (выступление)")
  @Size(
      max = 5,
      message = "{expert.registration.form.validation.error.applicant.performance.urls.size.exceeded}"
  )
  private List<String> performanceUrls;

  @Schema(
      description = "Комментарий по экспертизе в свободной форме.",
      example = "Варю хурму.",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{expert.registration.form.validation.error.applicant.comment.missing}")
  @Size(
      max = 1000,
      message = "{expert.registration.form.validation.error.applicant.comment.length.exceeded}"
  )
  private String comment;

  @Schema(
      description = "Прикрепляемый файл. Допустимые форматы: ppt, pptx, doc, docx, pdf. Макс. 20 МБ.",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  private MultipartFile attachment;

}
