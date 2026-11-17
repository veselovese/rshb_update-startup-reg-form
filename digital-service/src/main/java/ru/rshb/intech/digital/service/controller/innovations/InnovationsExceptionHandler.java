package ru.rshb.intech.digital.service.controller.innovations;

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
import ru.rshb.intech.digital.feature.innovations.core.exception.DemandNotFoundException;
import ru.rshb.intech.digital.feature.innovations.core.exception.InnovationValidationException;
import ru.rshb.intech.digtial.commons.dto.FormValidationErrorResponse;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

@Slf4j
@RestControllerAdvice(
    assignableTypes = {
        InnovationsController.class,
        InnovationsWindowController.class
    }
)
@Order(1)
public class InnovationsExceptionHandler {

  @Hidden
  @ApiResponse(
      responseCode = "400",
      description = "Переданные данные невалидны.",
      content = @Content
  )
  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(InnovationValidationException.class)
  public FormValidationErrorResponse handleValidationExceptions(@Nonnull InnovationValidationException e) {

    log.error(ErrorType.VALIDATION_ERROR.getTitle(), e);

    return new FormValidationErrorResponse(
        ErrorType.VALIDATION_ERROR.getTitle(),
        e.getViolations()
    );
  }

  @Hidden
  @ApiResponse(
      responseCode = "404",
      description = "Заявка с указанным ID не найдена",
      content = @Content
  )
  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(DemandNotFoundException.class)
  public OperationResultResponse handleDemandNotFound(@Nonnull DemandNotFoundException e) {
    return OperationResultResponse.error(e.getMessage());
  }
}
