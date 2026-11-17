package ru.rshb.intech.digital.service.controller.feedback;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rshb.intech.digital.commons.utils.ErrorType;
import ru.rshb.intech.digital.feedback.core.exception.FeedbackLimitException;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

@Slf4j
@RestControllerAdvice
@Order(1)
public class FeedbackExceptionHandler {

  @Hidden
  @ApiResponse(
      responseCode = "429",
      description = "Превышено число заявок в ограниченный интервал времени.",
      content = @Content
  )
  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
  @ExceptionHandler(FeedbackLimitException.class)
  public OperationResultResponse handleValidationExceptions(@Nonnull FeedbackLimitException e) {
    log.error(ErrorType.RATE_LIMIT_EXCEEDED.getTitle(), e);
    return OperationResultResponse.error(e.getMessage());
  }
}
