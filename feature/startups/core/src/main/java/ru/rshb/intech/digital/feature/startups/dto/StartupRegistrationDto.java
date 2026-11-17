package ru.rshb.intech.digital.feature.startups.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Generated;
import org.springframework.web.multipart.MultipartFile;
import ru.rshb.intech.digital.commons.validation.phone.Phone;
import ru.rshb.intech.digital.commons.validation.vk.VkUrl;

@Schema(name = "Данные заявки на размещение стартапа")
@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StartupRegistrationDto {

  @Schema(
      description = "ФИО отправителя заявки по стартапу",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{common.error.validation.full-name.missing}")
  private String applicantName;

  @Schema(
      description = "Должность отправителя",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  @Size(max = 255, message = "{startups.registration.validation.error.position.length.exceeded}")
  private String position;

  @Schema(
      description = "Организация отправителя",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{startups.registration.validation.error.org.missing}")
  @Size(max = 255, message = "{startups.registration.validation.error.org.length.exceeded}")
  private String organization;

  @Schema(
      description = "Электронная почта отправителя",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{common.error.validation.email.missing}")
  @Email(message = "{common.error.validation.email.format}")
  private String email;

  @Schema(
      description = "Контактный номер телефона отправителя",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{common.error.validation.phone.missing}")
  @Phone(message = "{common.error.validation.phone.format}")
  private String phone;

  @Schema(
      description = "Ссылка на профиль в VK",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  @VkUrl(message = "{common.error.validation.vk.url.bad.format}")
  private String vkProfileUrl;

  @Schema(
      description = "Название проекта стартапа",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{startups.registration.validation.error.project.name.missing}")
  @Size(max = 255, message = "{startups.registration.validation.error.project.length.exceeded}")
  private String projectName;

  @Schema(
      description = "Краткое описание проекта",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{startups.registration.validation.error.description.short.missing}")
  @Size(max = 70, message = "{startups.registration.validation.error.description.short.exceeded}")
  private String descriptionShort;

  @Schema(
      description = "Полное описание проекта",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{startups.registration.validation.error.description.full.missing}")
  @Size(max = 1000, message = "{startups.registration.validation.error.description.full.exceeded}")
  private String descriptionFull;

  @Schema(
      description = "Объем инвестиций",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  @Max(value = 1_000_000_000_000L, message = "{startups.registration.validation.error.investments.bad.value}")
  @Positive(message = "{startups.registration.validation.error.investments.bad.value}")
  private Long investmentsValue;

  @Schema(
      description = "Идентификатор типа валюты инвестиций из справочника",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  @Size(max = 100, message = "{startups.registration.validation.error.investments.currency.bad.value}")
  private String investmentsCurrencyId;

  @Schema(
      description = "Идентификатор категории стартапа из справочника",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  @Size(max = 100, message = "{startups.registration.validation.error.category.bad.value}")
  private String categoryId;

  @Schema(
      description = "Наименование категории стартапа в свободной форме",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  @Size(max = 60, message = "{startups.registration.validation.error.category.bad.value}")
  private String categoryCustom;

  @Schema(
      description = "Год основания стартапа",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @Positive(message = "{startups.registration.validation.error.foundation.year.bad.value}")
  private Integer foundedYear;

  @Schema(
      description = "Количество сотрудников",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  @Positive(message = "{startups.registration.validation.error.staff.size.bad.value}")
  private Integer staffSize;

  @Schema(
      description = "Идентификатор этапа проекта из справочника",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{startups.registration.validation.error.project.stage.missing}")
  private String projectStageId;

  @Schema(
      description = "Идентификатор варианта кол-ва клиентов из справочника",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{startups.registration.validation.error.clients.number.missing}")
  private String clientsId;

  @Schema(
      description = "Идентификатор раунда финансирования из справочника",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{startups.registration.validation.error.financing.round.missing}")
  private String financingId;

  @Schema(
      description = "Идентификатор страны",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{startups.registration.validation.error.country.missing}")
  @Size(max = 50, message = "{common.error.validation.country.bad.format}")
  private String countryId;

  @Schema(
      description = "Идентификатор города",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{startups.registration.validation.error.city.missing}")
  @Size(max = 50, message = "{common.error.validation.city.bad.format}")
  private String cityId;

  @Schema(
      description = "Прикрепляемый файл с логотипом стартапа. Макс. 20 МБ. Форматы: svg, eps, png",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotNull(message = "{startups.registration.validation.error.logo.attachment.missing}")
  private MultipartFile attachmentLogo;

  @Schema(
      description = "Прикрепляемый файл с презентацией стартапа. Макс. 20 МБ. Форматы: ppt, pptx, doc, docx, pdf",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  private MultipartFile attachmentPresentation;
}
