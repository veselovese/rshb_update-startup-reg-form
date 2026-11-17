package ru.rshb.intech.digital.service.controller.puzzles;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rshb.intech.digital.puzzles.core.crossword.exception.CrosswordException;
import ru.rshb.intech.digital.puzzles.core.crossword.exception.CrosswordNotFoundException;
import ru.rshb.intech.digital.puzzles.core.crossword.exception.CrosswordParticipantValidationException;
import ru.rshb.intech.digital.puzzles.core.crossword.exception.CrosswordQuestionNotFoundException;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

@RestControllerAdvice(
    assignableTypes = CrosswordController.class
)
@Order(1)
public class CrosswordExceptionHandler {

  @Hidden
  @ApiResponse(
      responseCode = "400",
      description = "Данные формы участника некорректны.",
      content = @Content
  )
  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(
      value = {
          CrosswordException.class,
          CrosswordParticipantValidationException.class,
      }
  )
  public OperationResultResponse handleCrosswordParticipantValidation(CrosswordException e) {
    return OperationResultResponse.error(e.getMessage());
  }

  @Hidden
  @ApiResponse(
      responseCode = "404",
      description = "Кроссворд/элемент кроссворда с указаным ID не найден.",
      content = @Content
  )
  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(
      value = {
          CrosswordNotFoundException.class,
          CrosswordQuestionNotFoundException.class
      }
  )
  public OperationResultResponse handleCrosswordElementNotFound(CrosswordException e) {
    return OperationResultResponse.error(e.getMessage());
  }
}
