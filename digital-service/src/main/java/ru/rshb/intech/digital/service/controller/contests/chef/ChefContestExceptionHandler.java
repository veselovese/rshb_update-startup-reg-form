package ru.rshb.intech.digital.service.controller.contests.chef;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rshb.intech.digital.feature.contests.chef.exception.ChefContestUrlCheckException;
import ru.rshb.intech.digital.feature.contests.chef.exception.ChefContestValidationException;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

@Slf4j
@RestControllerAdvice(assignableTypes = ChefContestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ChefContestExceptionHandler {

  @Hidden
  @ApiResponse(
      responseCode = "404",
      description = "Проверяемый URL не существует или возвращает неверные данные",
      content = @Content
  )
  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ExceptionHandler(ChefContestUrlCheckException.class)
  public ResponseEntity<OperationResultResponse> handleUrlCheckException(ChefContestUrlCheckException e) {
    log.error("Digital Chef solution URL validation error", e);
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(OperationResultResponse.error(e.getMessage()));
  }

  @Hidden
  @ApiResponse(
      responseCode = "400",
      description = "Переданные данные невалидны.",
      content = @Content
  )
  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ExceptionHandler(ChefContestValidationException.class)
  public ResponseEntity<OperationResultResponse> handleValidationException(ChefContestValidationException e) {
    log.error("Digital Chef data validation error", e);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(OperationResultResponse.error(e.getMessage()));
  }
}
