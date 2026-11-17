package ru.rshb.intech.digital.service.controller.statistics.agrobit;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feature.internship.core.dto.statistics.InternshipStatisticsDto;
import ru.rshb.intech.digital.feature.internship.core.service.statistics.InternshipStatisticsFilter;
import ru.rshb.intech.digital.feature.internship.core.service.statistics.InternshipStatisticsService;

@Tag(
    name = "Статистика для графиков. Агробит. Востребованность направлений",
    description = "API получения данных статистики для построения графиков."
)
@RestController
@RequestMapping(
    path = "${api.prefix}/statistics/internship",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
//@formatter:on
public class InternshipStatisticsController {

  private final InternshipStatisticsService internshipStatisticsService;


  //@formatter:off
  @Operation(
      summary = "Получить данные статистики по количеству заявок.",
      description = """
                    Возвращает данные по количеству заявок на стажировку за выбранный период времени.
                    """,
      parameters = {@Parameter(
          name = "field",
          description = "Одно или несколько направлений стажировки, разделенные запятыми.",
          example = "Тестирование,Разработка"
      )}
  )
  //@formatter:on
  @GetMapping
  public InternshipStatisticsDto getInternshipStats(@RequestParam(name = "fields", required = false) String fields) {

    final var filter = new InternshipStatisticsFilter(fields);
    return internshipStatisticsService.getMonthlyRelativeStatistics(filter);
  }
}
