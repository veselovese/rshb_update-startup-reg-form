package ru.rshb.intech.digital.service.controller.statistics.agrobit;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.dto.EduTypeStatsDto;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.service.AgrobitUniversityStatsService;

import java.util.List;
@Tag(
    name = "Статистика для графиков. Агробит. ВУЗы.",
    description = "API получения данных статистики для построения графиков."
)
@RestController
@RequestMapping(
    path = "${api.prefix}/statistics/agrobit/universities",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
//@formatter:on
public class UniversityStatsController {

  private final AgrobitUniversityStatsService universityStatsService;

  //@formatter:off
  @Operation(
      summary = "Получить данные статистики по ВУЗам.",
      description = "Возвращает данные статистики по ВУЗам."
  )
  //@formatter:on
  @GetMapping
  public List<EduTypeStatsDto> getUniversityStats() {
    return universityStatsService.getUniversityStats();
  }
}
