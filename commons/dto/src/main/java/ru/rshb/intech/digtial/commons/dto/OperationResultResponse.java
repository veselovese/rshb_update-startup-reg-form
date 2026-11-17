package ru.rshb.intech.digtial.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.messages.I18n;

import jakarta.annotation.Nonnull;


@Schema(
    name = "Результат выполнения операции"
)
@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Generated
public class OperationResultResponse {

  @Schema(
      description = "Статус операции."
  )
  private final ResultStatus status;

  @Schema(
      description = "Сообщение о результате операции.",
      example = "Опциональное описание результата."
  )
  private final String message;

  @Nonnull
  public static OperationResultResponse error(String message) {
    return new OperationResultResponse(ResultStatus.ERROR, message);
  }

  @Nonnull
  public static OperationResultResponse localizedError(@Nonnull String msgKey) {
    return new OperationResultResponse(ResultStatus.ERROR, I18n.of(msgKey));
  }

  @Nonnull
  public static OperationResultResponse success(String message) {
    return new OperationResultResponse(ResultStatus.SUCCESS, message);
  }

  @Nonnull
  public static OperationResultResponse success() {
    return new OperationResultResponse(ResultStatus.SUCCESS, null);
  }

  @Nonnull
  public static OperationResultResponse localizedSuccess(@Nonnull String msgKey) {
    return new OperationResultResponse(ResultStatus.SUCCESS, I18n.of(msgKey));
  }
}
