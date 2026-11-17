package ru.rshb.intech.digital.service.controller.contests.stipend;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rshb.intech.digital.subsystem.contests.stipend.exception.TaskNotFoundException;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

@Slf4j
@RestControllerAdvice(assignableTypes = StipendContestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StipendContestExceptionHandler {

  @Hidden
  @ApiResponse(
      responseCode = "404",
      description = "Задача с указанным taskId не найдена",
      content = @Content
  )
  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ExceptionHandler(TaskNotFoundException.class)
  public ResponseEntity<OperationResultResponse> handleTaskNotFound(@Nonnull TaskNotFoundException e) {
    log.error("Stipend contest task not found", e);
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(OperationResultResponse.error(e.getMessage()));
  }

}
