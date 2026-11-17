package ru.rshb.intech.digtial.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Generated;
import lombok.Getter;

import jakarta.annotation.Nonnull;
import java.util.Map;


@Schema(name = "Ошибка валидации полей формы")
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Generated
public class FormValidationErrorResponse extends OperationResultResponse {

  @Schema(
      description = "Имена полей и сообщения об ошибках для каждого поля.",
      example = """
                {
                   "email": "Некорректный формат адреса электронной почты",
                   "applicantName": "Не указано имя заявителя"
                }
                """
  )
  private final Map<String, String> errors;

  public FormValidationErrorResponse(@Nonnull String message, @Nonnull Map<String, String> errors) {
    super(ResultStatus.ERROR, message);
    this.errors = errors;
  }
}
