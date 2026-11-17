package ru.rshb.intech.digital.feature.contests.chef.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.rshb.intech.digital.commons.validation.phone.Phone;
import ru.rshb.intech.digital.commons.validation.url.Url;

@Schema(name = "Данные участника конкурса \"Повар\"")
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class ChefContestantFormData {

  @Schema(
      description = "URL решения",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{contest.chef.error.validation.solution.url.missing}")
  @Url(message = "{contest.chef.error.validation.solution.url.format}")
  private String url;

  @Schema(
      description = "Кол-во правильных ответов при предварительной проверке URL",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotNull(message = "{contest.chef.error.validation.scores.missing}")
  @Min(
      value = 0,
      message = "{contest.chef.error.validation.scores.invalid.amount}"
  )
  private Integer level;

  @Schema(
      description = "Имя участника",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{contest.chef.error.validation.contestant.name.missing}")
  private String contestantName;

  @Schema(
      description = "Контактный номер телефона участника",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotBlank(message = "{contest.chef.error.validation.contestant.phone.missing}")
  @Phone(message = "{contest.chef.error.validation.contestant.phone.format}")
  private String phone;


  @Schema(
      description = "Файл с резюме участника. Max. 10 МБ.",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotNull(message = "{attachment.error.missing}")
  private MultipartFile attachment;
}
