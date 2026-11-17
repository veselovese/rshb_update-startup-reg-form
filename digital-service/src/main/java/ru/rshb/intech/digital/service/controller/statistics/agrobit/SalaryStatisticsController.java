package ru.rshb.intech.digital.service.controller.statistics.agrobit;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.statistics.agrobit.commons.PlotBundleDto;
import ru.rshb.intech.digital.statistics.agrobit.salary.dto.SalaryStatsFilter;
import ru.rshb.intech.digital.statistics.agrobit.salary.dto.category.SalaryPositionCategoryLookupDto;
import ru.rshb.intech.digital.statistics.agrobit.salary.service.category.SalaryPositionCategoryService;
import ru.rshb.intech.digital.statistics.agrobit.salary.service.sfct.SfctSalaryStatisticsService;

@Tag(
    name = "Статистика для графиков. Агробит. Заработная плата.",
    description = "API получения данных статистики для построения графиков."
)
@RestController
@RequestMapping(
    path = "${api.prefix}/statistics/agrobit/salary",
    produces = MediaType.APPLICATION_JSON_VALUE
)
//@formatter:on
@RequiredArgsConstructor
public class SalaryStatisticsController {

  private final SfctSalaryStatisticsService salaryStatisticsService;
  private final SalaryPositionCategoryService salaryPositionCategoryService;


  //@formatter:off
  @Operation(
      summary = "Получить данные статистики тенденциям рынка труда в агросфере.",
      description = """
                    Возвращает данные по среднему размеру зарплаты в агросфере по годам и категориям.
                    """,
      parameters = {
          @Parameter(
              name = "field",
              description = "Категория профессии",
              example = "Животноводство",
              in = ParameterIn.QUERY
          )
      }
  )
  //@formatter:on
  @GetMapping("/avg")
  public PlotBundleDto getAgrobitStats(@RequestParam(name = "field", required = false) String fields) {

    return salaryStatisticsService.getAvgSalaryStatsByCategories(fields);
  }

  //@formatter:off
  @Operation(
      summary = "Получить данные статистики тенденциям рынка труда в агросфере по профессиям.",
      description = """
                    Возвращает данные по среднему размеру зарплаты в агросфере по профессиям и категориям.
                    """,
      parameters = {
          @Parameter(
              name = "categoryId",
              description = "Внешний идентификатор категории профессий",
              example = "7b96f6b6-ad4f-4f28-9f66-13e2d1559cef",
              in = ParameterIn.QUERY
          ),
          @Parameter(
              name = "positionIds",
              description = "Один или несколько внешних идентификаторов профессий",
              example = "29bef1c0-edac-4b29-a593-e35722cbd75b,e045d7c6-9fb5-4872-90f9-cbfad9ee646e"
          )
      }
  )
  //@formatter:on
  @GetMapping("/position")
  public PlotBundleDto getAgrobitSalaryTrends(@RequestParam(name = "categoryId", required = false) String categoryId,
                                              @RequestParam(name = "positionIds", required = false) String positionIds) {


    final var filter = new SalaryStatsFilter(categoryId);
    filter.setPositionIds(positionIds);

    return salaryStatisticsService.getAvgSalaryStatistics(filter);
  }

  //@formatter:off
  @Operation(
      summary = "Получить справочник категорий профессий для статистики по зарплате для раздела Агробит",
      description = """
                    Возвращает справочник категорий и наименований профессий.
                    """
  )
  //@formatter:on
  @GetMapping("/position/categories")
  public SalaryPositionCategoryLookupDto getAgrobitSalaryPositionCategories() {

    return salaryPositionCategoryService.getPositionCategoryLookup();
  }

}
