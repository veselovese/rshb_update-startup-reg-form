package ru.rshb.intech.digital.feature.internship.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Generated;
import org.springframework.web.multipart.MultipartFile;
import ru.rshb.intech.digital.commons.validation.phone.Phone;

@Schema(name = "Отзыв по результатам стажировки")
@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InternshipFeedbackDto {

  @Schema(description = "Насколько были интересны рабочие задачи")
  @Min(value = 1, message = "{internship.feedback.validation.error.task.grade.bad.format}")
  @Max(value = 5, message = "{internship.feedback.validation.error.task.grade.bad.format}")
  @NotNull(message = "{internship.feedback.validation.error.task.grade.missing}")
  private Integer tasksGrade;

  @Schema(description = "Навыки, приобретённые в ходе прохождения стажировки")
  @NotBlank(message = "{internship.feedback.validation.error.skills.missing}")
  @Size(max = 1000, message = "{internship.feedback.validation.error.skills.max.length.exceeded}")
  private String skills;

  @Schema(description = "Кто был наставником")
  @Size(max = 255, message = "{internship.feedback.validation.error.mentor.max.length.exceeded}")
  private String mentor;

  @Schema(description = "Оценка работы наставника")
  @Min(value = 1, message = "{internship.feedback.validation.error.mentor.grade.bad.format}")
  @Max(value = 5, message = "{internship.feedback.validation.error.mentor.grade.bad.format}")
  @NotNull(message = "{internship.feedback.validation.error.mentor.grade.missing}")
  private Integer mentorWorkGrade;

  @Schema(description = "Подразделение и команда, где проходили стажировку")
  @NotBlank(message = "{internship.feedback.validation.error.team.division.missing}")
  @Size(max = 255, message = "{internship.feedback.validation.error.team.division.max.length.exceeded}")
  private String teamAndDivision;

  @Schema(description = "Уровень коммуникации в команде")
  @Min(value = 1, message = "{internship.feedback.validation.error.team.comm.grade.bad.format}")
  @Max(value = 5, message = "{internship.feedback.validation.error.team.comm.grade.bad.format}")
  @NotNull(message = "{internship.feedback.validation.error.team.comm.grade.missing}")
  private Integer teamCommunicationGrade;

  @Schema(description = "Уровень ожидания при прохождении стажировки")
  @NotNull(message = "{internship.feedback.validation.error.expect.level.grade.missing}")
  @Min(value = 1, message = "{internship.feedback.validation.error.expect.level.grade.bad.format}")
  @Max(value = 5, message = "{internship.feedback.validation.error.expect.level.grade.bad.format}")
  private Integer expectationLevelGrade;

  @Schema(description = "Комментария по улучшению программы стажировки")
  @Size(max = 255, message = "{internship.feedback.validation.error.comment.max.length.exceeded}")
  private String comment;

  @Schema(description = "ФИО стажера отправителя формы обратной связи")
  @NotBlank(message = "{common.error.validation.full-name.missing}")
  @Size(max = 255, message = "{common.error.validation.full-name.max.length.exceeded}")
  private String traineeName;

  @Schema(description = "Контактный номер телефона")
  @NotBlank(message = "{common.error.validation.phone.missing}")
  @Phone(message = "{common.error.validation.phone.format}")
  private String phone;

  @Schema(description = "Электронная почта отправителя")
  @NotBlank(message = "{common.error.validation.email.missing}")
  @Email(message = "{common.error.validation.email.format}")
  private String email;

  @Schema(description = "Откуда узнали о программе стажировки.")
  @NotBlank(message = "{internship.feedback.validation.error.recommend.src.missing}")
  @Size(max = 255, message = "{internship.feedback.validation.error.recommend.src.nax.length.exceeded}")
  private String recommendationSource;

  @Schema(description = "Рекомендовали бы Вы программу стажировки знакомым")
  @Size(max = 1000, message = "{internship.feedback.validation.error.recommendation.nax.length.exceeded}")
  private String recommendation;

  @Schema(description = "Поле отзыва для публикации на сайте")
  @Size(max = 1000, message = "{internship.feedback.validation.error.review.nax.length.exceeded}")
  private String review;

  @Schema(description = "Прикрепляемое фото")
  private MultipartFile attachmentPhoto;
}
