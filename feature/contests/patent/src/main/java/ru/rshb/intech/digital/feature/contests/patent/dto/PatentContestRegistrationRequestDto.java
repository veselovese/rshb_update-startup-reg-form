package ru.rshb.intech.digital.feature.contests.patent.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Generated;
import org.springframework.web.multipart.MultipartFile;
import ru.rshb.intech.digital.commons.validation.phone.Phone;
import ru.rshb.intech.digital.commons.validation.text.SizeAfterReduce;
import ru.rshb.intech.digital.commons.validation.url.Url;

@Schema(name = "Данные регистрации на патентный конкурс")
@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatentContestRegistrationRequestDto {

  @Schema(
      description = "ФИО",
      example = "Иванов Иван Иванович",
      maxLength = 255,
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{common.error.validation.full-name.missing}")
  @SizeAfterReduce(
      max = 255,
      message = "{common.error.validation.full-name.max.length.exceeded}"
  )
  private String applicantName;

  @Schema(
      description = "Должность",
      example = "Генеральный директор",
      maxLength = 100
  )
  @SizeAfterReduce(
      max = 100,
      message = "{patents.contest.registration.validation.error.position.length.exceeded}"
  )
  private String position;

  @Schema(
      description = "Наименование организации",
      example = "OOO Лорем Ипсум",
      maxLength = 100
  )
  @SizeAfterReduce(
      max = 100,
      message = "{patents.contest.registration.validation.error.organization.length.exceeded}"
  )
  private String organization;

  @Schema(
      description = "Наименование проекта",
      example = "Корпускулярно-волновой ионизатор бульбы",
      maxLength = 255,
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{contest.patent.registration.validation.error.project.name.missing}")
  @SizeAfterReduce(
      max = 255,
      message = "{contest.patent.registration.validation.error.project.name.length.exceeded}"
  )
  private String projectName;

  @Schema(
      description = "ID этапа проекта (из справочника).",
      example = "86b70636-69b4-4c43-a0f9-c0a3c68fee5e",
      maxLength = 100,
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{contest.patent.registration.validation.error.project.stage-id.missing}")
  @Size(
      max = 100,
      message = "{contest.patent.registration.validation.error.project.stage-id.length.exceeded}"
  )
  private String projectStageId;

  @Schema(
      description = "Номер патента",
      example = "2201527",
      maxLength = 100,
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{contest.patent.registration.validation.error.patent.number.missing}")
  @SizeAfterReduce(
      max = 100,
      message = "{contest.patent.registration.validation.error.patent.number.length.exceeded}"
  )
  private String patentNumber;

  @Schema(
      description = "Год патентирования",
      example = "2022",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @Positive(message = "{contest.patent.registration.validation.error.patent.year.bad.format}") // TODO валидировать год
  private Integer patentYear;

  @Schema(
      description = "МПК патента",
      example = "F03H 3/00 (2000.01)",
      maxLength = 100,
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{contest.patent.registration.validation.error.patent.mpk.missing}")
  @SizeAfterReduce(
      max = 100,
      message = "{contest.patent.registration.validation.error.patent.mpk.length.exceeded}"
  )
  private String patentMpk;

  @Schema(
      description = "Патентообладатель",
      example = "Иванов И.И.",
      maxLength = 100,
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{contest.patent.registration.validation.error.patent.holder.missing}")
  @SizeAfterReduce(
      max = 100,
      message = "{contest.patent.registration.validation.error.patent.holder.length.exceeded}"
  )
  private String patentHolder;

  @Schema(
      description = "Краткое описание",
      example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
      maxLength = 300,
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{contest.patent.registration.validation.error.description.short.missing}")
  @SizeAfterReduce(
      max = 300,
      message = "{contest.patent.registration.validation.error.description.short.length.exceeded}"
  )
  private String descriptionShort;

  @Schema(
      description = "ID типа хозяйствующего субъекта (из справочника)",
      example = "86b70636-69b4-4c43-a0f9-c0a3c68fee5e",
      maxLength = 100,
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{contest.patent.registration.validation.error.managing.subject.missing}")
  @Size(
      max = 100,
      message = "{contest.patent.registration.validation.error.managing.subject.length.exceeded}"
  )
  private String managingSubjectId;

  @Schema(
      description = "Описание изобретения",
      example = "Lorem ipsum",
      maxLength = 3000,
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{contest.patent.registration.validation.error.invention.missing}")
  @SizeAfterReduce(
      max = 3000,
      message = "{contest.patent.registration.validation.error.invention.length.exceeded}"
  )
  private String invention;

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
      description = "Ссылка на сайт",
      example = "https://google.com",
      maxLength = 2048
  )
  @Url(message = "{contest.patent.registration.validation.error.site.url.bad.format}")
  private String siteUrl;

  @Schema(
      description = "ID варианта коммерческой реализации (из справочника)",
      example = "86b70636-69b4-4c43-a0f9-c0a3c68fee5e",
      maxLength = 100,
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{contest.patent.registration.validation.error.commercial.impl.missing}")
  @Size(
      max = 100,
      message = "{contest.patent.registration.validation.error.commercial.impl.exceeded}"
  )
  private String commercialImplId;

  @Schema(
      description = "Объем инвестиций",
      example = "100 млрд. тугриков",
      maxLength = 30
  )
  @SizeAfterReduce(
      max = 30,
      message = "{contest.patent.registration.validation.error.investments.length.exceeded}"
  )
  private String investmentsValue;

  @Schema(
      description = "ID варианта раунда финансирования (из справочника)",
      example = "86b70636-69b4-4c43-a0f9-c0a3c68fee5e",
      maxLength = 100,
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{contest.patent.registration.validation.error.investment.round.missing}")
  @Size(
      max = 100,
      message = "{contest.patent.registration.validation.error.investment.round.exceeded}"
  )
  private String financingRoundId;

  @Schema(
      description = "ID варианта размера клиентской базы (из справочника)",
      example = "86b70636-69b4-4c43-a0f9-c0a3c68fee5e",
      maxLength = 100,
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{contest.patent.registration.validation.error.clients.num.missing}")
  @Size(
      max = 100,
      message = "{contest.patent.registration.validation.error.clients.num.exceeded}"
  )
  private String clientsNumVariantId;

  @Schema(
      description = "Количество сотрудников",
      example = "5"
  )
  @Max(
      value = 999_999,
      message = "{contest.patent.registration.validation.error.staff.bad.format}"
  )
  @Positive(message = "{contest.patent.registration.validation.error.staff.bad.format}")
  private Integer staffSize;

  @Schema(
      description = "Победы в конкурсах",
      example = "Лауреат Второго Всероссийского Ордена Жириновского конкурса сажателей картофеля в номинации \"Ночной марафон\"",
      maxLength = 255
  )
  @SizeAfterReduce(
      max = 255,
      message = "{contest.patent.registration.validation.error.victories.exceeded}"
  )
  private String victoriesInContests;

  @Schema(
      description = "ID города (из справочника)",
      example = "45.000.000.000.1",
      maxLength = 100,
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{contest.patent.registration.validation.error.city-id.missing}")
  @Size(
      max = 100,
      message = "{contest.patent.registration.validation.error.city-id.exceeded}"
  )
  private String cityId;

  @Schema(
      description = "Файл с логотипом. Допустимые типы: svg, eps, png. Размер: 20 МБ."
  )
  private MultipartFile attachmentLogo;

  @Schema(
      description = "Файл с презентацией. Допустимые типы:  ppt, pptx, doc, docx, pdf. Размер: 20 МБ"
  )
  private MultipartFile attachmentPresentation;

}
