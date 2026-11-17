package ru.rshb.intech.digital.service.controller.puzzles;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.puzzles.core.farm.DigitalFarmStatsService;
import ru.rshb.intech.digital.puzzles.dto.farm.CommonUsageStatsDto;

@Tag(
    name = "Игры. Цифровая ферма",
    description = "API для получения данных игры \"Цифровая Ферма\""
)
@RequestMapping(
    path = "${api.external.prefix}/puzzle/digital-farm",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RestController
@RequiredArgsConstructor
public class DigitalFarmGameController {

  private final DigitalFarmStatsService digitalFarmStatsService;


  @Operation(
      summary = "Получить статистику использования по игре.",
      description = "Возвращает данные общей статистики.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Операция успешно выполнена."
          )
      }
  )
  @GetMapping("/stats")
  public CommonUsageStatsDto getStats() {
    return digitalFarmStatsService.getCommonUsageStats();
  }
}
