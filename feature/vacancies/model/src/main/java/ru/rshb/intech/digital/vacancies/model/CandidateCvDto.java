package ru.rshb.intech.digital.vacancies.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Generated;
import org.springframework.web.multipart.MultipartFile;
import ru.rshb.intech.digital.commons.validation.phone.Phone;
import ru.rshb.intech.digital.commons.validation.text.SizeAfterReduce;

@Schema(name = "Данные соискателя")
@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateCvDto {

  @Schema(
      description = "ФИО",
      example = "Сидоров Иван Петрович"
  )
  @NotBlank(message = "{common.error.validation.full-name.missing}")
  @SizeAfterReduce(max = 255, message = "common.error.validation.full-name.max.length.exceeded")
  private String candidateName;

  @Schema(
      description = "Контактный номер телефона",
      example = "+7 (999) 666-77-88"
  )
  @NotBlank(message = "{common.error.validation.phone.missing}")
  @Phone(message = "{common.error.validation.phone.format}")
  private String phone;

  @Schema(
      description = "Адрес электронной почты",
      example = "piligrim365@yandex.ru"
  )
  @NotBlank(message = "{common.error.validation.email.missing}")
  @Email(message = "{common.error.validation.email.format}")
  private String email;

  @Schema(
      description = "Сопроводительное письмо",
      example = "Lorem ipsum dolor amit"
  )
  @NotBlank(message = "{vacancies.candidate.validation.error.covering-letter.missing}")
  @SizeAfterReduce(max = 1000, message = "{vacancies.candidate.validation.error.covering-letter.length.exceeded}")
  private String coveringLetter;

  @NotNull(message = "{vacancies.candidate.validation.error.cv.missing}")
  private MultipartFile attachmentResume;

}
