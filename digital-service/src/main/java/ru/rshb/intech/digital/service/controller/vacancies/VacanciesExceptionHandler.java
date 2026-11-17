package ru.rshb.intech.digital.service.controller.vacancies;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rshb.intech.digital.vacancies.core.exception.VacancyNotFoundException;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

@RestControllerAdvice(
    assignableTypes = VacanciesController.class
)
@Order(1)
public class VacanciesExceptionHandler {

  @Hidden
  @ApiResponse(
      responseCode = "404",
      description = "Вакансия не найдена",
      content = @Content
  )
  @ExceptionHandler(VacancyNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public OperationResultResponse handleVacancyNotFound(VacancyNotFoundException e) {
    return OperationResultResponse.error(e.getMessage());
  }
}
