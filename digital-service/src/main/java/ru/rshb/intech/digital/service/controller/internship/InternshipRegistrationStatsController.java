package ru.rshb.intech.digital.service.controller.internship;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feature.internship.core.service.registration.RegistrationService;
import ru.rshb.intech.digtial.commons.dto.DistributionStatisticsDto;

@Tag(
    name = "Стажировка",
    description = "API для операций, связанных со стажировкой"
)
@RestController
@RequestMapping(
    path = "${api.prefix}/internship/registration/stats",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class InternshipRegistrationStatsController {

  private final RegistrationService registrationService;

  @Operation(
      summary = "Получить распределение количества заявок на стажировку по направлениям.",
      description = "Возвращает распределение количества заявок на стажировку по направлениям.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешное выполнение операции."
          )
      }
  )
  @GetMapping("/fields")
  public DistributionStatisticsDto getStatsByFields() {
    return registrationService.findDistributionByFields();
  }
}
