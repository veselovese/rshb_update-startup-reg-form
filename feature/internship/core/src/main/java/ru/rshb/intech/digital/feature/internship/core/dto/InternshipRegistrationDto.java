package ru.rshb.intech.digital.feature.internship.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Generated;
import org.springframework.web.multipart.MultipartFile;
import ru.rshb.intech.digital.commons.validation.date.SimpleDate;
import ru.rshb.intech.digital.commons.validation.phone.Phone;
import ru.rshb.intech.digital.commons.validation.telegram.Telegram;

@Schema(name = "Данные формы заявки на стажировку")
@Data
@Generated
public class InternshipRegistrationDto {

  @Schema(
      description = "Направление стажировки. Значение должно браться из соответствующего справочника.",
      example = "Дизайн",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{internship.registration.error.validation.internship.field.missing}")
  String internshipField;

  @Schema(
      description = "ФИО заявителя.",
      example = "Владимиров Владимир Владимирович",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{common.error.validation.full-name.missing}")
  private String applicantName;

  @Schema(
      description = "Контактный номер телефона заявителя.",
      example = "+79996664214",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{common.error.validation.phone.missing}")
  @Phone(message = "{common.error.validation.phone.format}")
  private String phone;

  @Schema(
      description = "Адрес электронной почты заявителя.",
      example = "alex@none.com",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{common.error.validation.email.missing}")
  @Email(message = "{common.error.validation.email.format}")
  private String email;

  @Schema(
      description = "Комментарий/дополнительные сведения заявителя в свободной форме.",
      example = "Хочу стать дизайнером.",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  private String description;

  @Schema(
      description = "Telegram. Полная ссылка с t.me или тег.",
      example = "@alex1975 или https://t.me/alex1975",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  @Telegram(message = "{common.error.validation.telegram.url.format}")
  private String telegram;

  @Schema(
      description = "Дата рождения",
      example = "13.12.2002",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotNull(message = "{internship.registration.error.validation.birthdate.missing}")
  @SimpleDate(message = "{internship.registration.error.validation.birthdate.format}")
  private String birthDate;

  @Schema(
      description = "ID города из справочника",
      example = "f29a76c4-1507-42e4-ae7f-d8d55e4ed474",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  private String cityId;

  @Schema(
      description = "Наименование города (если город с таким наименованием отсутствует в справочнике)",
      example = "Новоквадрицепсево",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  private String cityCustom;

  @Schema(
      description = "ID ВУЗа из справочника",
      example = "f29a76c4-1507-42e4-ae7f-d8d55e4ed474",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  private String universityId;

  @Schema(
      description = "Наименование ВУЗа (если ВУЗ с таким наименованием отсутствует в справочнике)",
      example = "Квадрицепстаунский Политех",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  private String universityCustom;

  @Schema(
      description = "Наименование ступени обучения из справочника",
      example = "специалитет",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  private String educationStage;

  @Schema(
      description = "Курс. Если ступень обучения не подразумевает наличия курса - поле отсутствует и игнорируется при обработке",
      example = "3",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  private Integer educationStageYear;

  @Schema(
      description = "Знания и навыки",
      example = "Умею в нарды.",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{internship.registration.error.validation.about.skills.missing}")
  private String aboutSkills;

  @Schema(
      description = "Какими задачами интересно было бы заниматься.",
      example = "Хочу осилить шахматы.",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{internship.registration.error.validation.about.desired.tasks.missing}")
  private String aboutWishedTasks;

  @Schema(
      description = "Примеры из практики.",
      example = "Вот тут мои наработки: ...",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  private String aboutPortfolioExamples;

  @Schema(
      description = "Опыт работы или стажировки",
      example = "Работал там-то...",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  private String aboutExperience;

  @Schema(
      description = "Количество рабочих часов. Значение из справочника.",
      example = "20 часов",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{internship.registration.error.validation.pref-work-hours.missing}")
  @JsonProperty("prefHrsWk")
  private String prefHrsWk;

  @Schema(description = "Файл с резюме заявителя на стажировку. Не болеее 10 МБ.")
  @NotNull(message = "{attachment.error.missing}")
  private MultipartFile attachData;

  @Schema(description = "Файл с пасспортом компетенций. Только pdf. Не более 20 МБ.")
  private MultipartFile skillPassportFile;
}
