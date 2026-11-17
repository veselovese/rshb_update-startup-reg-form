package ru.rshb.intech.digital.feature.startups.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Generated;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.web.multipart.MultipartFile;
import ru.rshb.intech.digital.commons.validation.phone.Phone;

import java.util.List;

@Schema(name = "Данные заявки на размещение стартапа")
@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StartupRegistrationDto {

  @Schema(description = "ФИО контактного лица", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotBlank(message = "{common.error.validation.full-name.missing}")
  @Size(max = 255)
  private String applicantName;

  @Schema(description = "Электронная почта контактного лица", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotBlank(message = "{common.error.validation.email.missing}")
  @Email(message = "{common.error.validation.email.format}")
  private String email;

  @Schema(description = "Контактный телефон", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotBlank(message = "{common.error.validation.phone.missing}")
  @Phone(message = "{common.error.validation.phone.format}")
  private String phone;

  @Schema(description = "Город", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotBlank(message = "{startups.registration.validation.error.city.missing}")
  @Size(max = 255)
  private String city;

  @Schema(description = "Юридическое лицо", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @Size(max = 255, message = "{startups.registration.validation.error.org.length.exceeded}")
  private String organization;

  @Schema(description = "ИНН", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @Pattern(regexp = "^\\s*$|^\\d{10}(\\d{2})?$", message = "{startups.registration.validation.error.inn.bad.value}")
  private String inn;

  @Schema(description = "Название проекта", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotBlank(message = "{startups.registration.validation.error.project.name.missing}")
  @Size(max = 255, message = "{startups.registration.validation.error.project.length.exceeded}")
  private String projectName;

  @Schema(description = "Год основания", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull(message = "{startups.registration.validation.error.foundation.year.bad.value}")
  @Positive(message = "{startups.registration.validation.error.foundation.year.bad.value}")
  private Integer foundedYear;

  @Schema(description = "Краткое описание проекта", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotBlank(message = "{startups.registration.validation.error.description.short.missing}")
  @Size(max = 300, message = "{startups.registration.validation.error.description.short.exceeded}")
  private String descriptionShort;

  @Schema(description = "Полное описание проекта", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotBlank(message = "{startups.registration.validation.error.description.full.missing}")
  @Size(max = 1000, message = "{startups.registration.validation.error.description.full.exceeded}")
  private String descriptionFull;

  @Schema(description = "Сайт проекта", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @Pattern(regexp = "^\\s*$|^https?://[A-Za-z0-9.-]+(?::\\d+)?(?:[/?#]\\S*)?$",
      message = "{startups.registration.validation.error.website.bad.value}")
  @Size(max = 2048)
  private String websiteUrl;

  @Schema(description = "ФИО инвестора", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @Size(max = 255)
  private String investorName;

  @Schema(description = "Объём привлечённых инвестиций", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @PositiveOrZero(message = "{startups.registration.validation.error.investments.bad.value}")
  private Long investmentsValue;

  @Schema(description = "Выручка за прошлый год", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotBlank(message = "{startups.registration.validation.error.revenue.missing}")
  @Pattern(
      regexp = "Нет|До 1 млн|1-10 млн|10-50 млн|50-100 млн|>100 млн",
      message = "{startups.registration.validation.error.revenue.bad.value}"
  )
  private String revenueRange;

  @Schema(description = "Идентификаторы категорий проекта", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotEmpty(message = "{startups.registration.validation.error.category.missing}")
  @Size(max = 18, message = "{startups.registration.validation.error.category.bad.value}")
  @UniqueElements(message = "{startups.registration.validation.error.category.bad.value}")
  private List<@NotBlank(message = "{startups.registration.validation.error.category.bad.value}") String> categoryIds;

  @Schema(description = "Идентификатор стадии проекта", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotBlank(message = "{startups.registration.validation.error.project.stage.missing}")
  private String projectStageId;

  @Schema(description = "Наличие решений с ИИ", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull(message = "{startups.registration.validation.error.ai.missing}")
  private Boolean hasAiSolutions;

  @Schema(description = "Ссылка на видео о проекте (Rutube или VK Видео)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @Pattern(
      regexp = "^\\s*$|^https?://(www\\.)?(rutube\\.ru|vkvideo\\.ru)(?:[/?#]\\S*)?$"
          + "|^https?://(www\\.)?vk\\.com/video\\S*$",
      message = "{startups.registration.validation.error.video.bad.value}"
  )
  @Size(max = 2048)
  private String videoUrl;

  @Schema(
      description = "Логотип проекта. Макс. 20 МБ. Форматы: svg, eps, png",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  private MultipartFile attachmentLogo;
}
