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
import ru.rshb.intech.digital.statistics.agrobit.tourism.dto.TourismTypeDto;
import ru.rshb.intech.digital.statistics.agrobit.tourism.entity.TourismStatsFilter;
import ru.rshb.intech.digital.statistics.agrobit.tourism.service.TourismCategoryService;
import ru.rshb.intech.digital.statistics.agrobit.tourism.service.TourismStatsService;

import java.util.List;

//@formatter:off
@Tag(
    name = "Статистика для графиков. Агробит. Туризм.",
    description = "API получения данных статистики для построения графиков."
)
@RestController
@RequestMapping(
    path = "${api.prefix}/statistics/agrobit/tourism",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
//@formatter:on
public class TourismStatisticsController {

  private final TourismCategoryService tourismCategoryService;
  private final TourismStatsService tourismStatsService;


  //@formatter:off
  @Operation(
      summary = "Получить справочник типов туризма и видов отдыха.",
      description = "Возвращает полный справочник типов туризма и видов отдыха."
  )
  //@formatter:on
  @GetMapping("/categories")
  public List<TourismTypeDto> getTourismCategories() {
    return tourismCategoryService.getAllCategories();
  }

  //@formatter:off
  @Operation(
      summary = "Получить усредненные данные статистики о среднем чеке по категории и виду отдыха.",
      description = "Возвращает усредненные данные статистики о среднем чеке на человека по типу туризма."
  )
  //@formatter:on
  @GetMapping("/check/avg")
  public PlotBundleDto getCheckStatsAvg() {
    return tourismStatsService.getCheckStatsAvg();
  }

  //@formatter:off
  @Operation(
      summary = "Получить данные статистики о среднем чеке по категории и виду отдыха.",
      description = "Возвращает данные статистики о среднем чеке на человека по типу туризма.",
      parameters = {
          @Parameter(
              name = "categoryId",
              description = "Внешний идентификатор общего типа туризма. Значение из справочника.",
              example = "7b96f6b6-ad4f-4f28-9f66-13e2d1559cef",
              in = ParameterIn.QUERY
          ),
          @Parameter(
              name = "recreationId",
              description = "Внешний идентификатор вида отдыха. Значение из справочника.",
              example = "29bef1c0-edac-4b29-a593-e35722cbd75b,e045d7c6-9fb5-4872-90f9-cbfad9ee646e",
              in = ParameterIn.QUERY
          )
      }
  )
  //@formatter:on
  @GetMapping("/check/category")
  public PlotBundleDto getCheckStatsByCategory(@RequestParam(name = "categoryId", required = false) String categoryId,
                                               @RequestParam(name = "recreationId", required = false) String recreationId) {

    final var filter = new TourismStatsFilter(categoryId);
    filter.setRecreationTypeIds(recreationId);

    return tourismStatsService.getCheckStats(filter);
  }

  //@formatter:off
  @Operation(
      summary = "Получить усредненные данные статистики о средней стоимости путевки.",
      description = "Возвращает усредненные данные статистики о средней стоимости путевки."
  )
  //@formatter:on
  @GetMapping("/price/avg")
  public PlotBundleDto getPriceStatsAvg() {
    return tourismStatsService.getPriceStatsAvg();
  }

  //@formatter:off
  @Operation(
      summary = "Получить данные статистики о средней стоимости путевки по категории и виду отдыха.",
      description = "Возвращает данные статистики о средней стоимости путевки по типу туризма.",
      parameters = {
          @Parameter(
              name = "categoryId",
              description = "Внешний идентификатор общего типа туризма. Значение из справочника.",
              example = "7b96f6b6-ad4f-4f28-9f66-13e2d1559cef",
              in = ParameterIn.QUERY
          ),
          @Parameter(
              name = "recreationId",
              description = "Внешний идентификатор вида отдыха. Значение из справочника.",
              example = "29bef1c0-edac-4b29-a593-e35722cbd75b,e045d7c6-9fb5-4872-90f9-cbfad9ee646e",
              in = ParameterIn.QUERY
          )
      }
  )
  //@formatter:on
  @GetMapping("/price/category")
  public PlotBundleDto getPriceStatsByCategory(@RequestParam(name = "categoryId", required = false) String categoryId,
                                               @RequestParam(name = "recreationId", required = false) String recreationId) {

    final var filter = new TourismStatsFilter(categoryId);
    filter.setRecreationTypeIds(recreationId);

    return tourismStatsService.getPriceStats(filter);
  }

  //@formatter:off
  @Operation(
      summary = "Получить усредненные данные статистики о количестве представленных туров.",
      description = "Возвращает усредненные данные статистики о количестве представленных туров."
  )
  //@formatter:on
  @GetMapping("/tours/avg")
  public PlotBundleDto getTourNumberStatsAvg() {
    return tourismStatsService.getTourNumberStatsAvg();
  }

  //@formatter:off
  @Operation(
      summary = "Получить данные статистики о количестве представленных туров по категории и виду отдыха.",
      description = """
                    Возвращает данные статистики о количестве представленных туров по типу туризма.
                    """,
      parameters = {
          @Parameter(
              name = "categoryId",
              description = "Внешний идентификатор общего типа туризма. Значение из справочника.",
              example = "7b96f6b6-ad4f-4f28-9f66-13e2d1559cef",
              in = ParameterIn.QUERY
          ),
          @Parameter(
              name = "recreationId",
              description = "Внешний идентификатор вида отдыха. Значение из справочника.",
              example = "29bef1c0-edac-4b29-a593-e35722cbd75b,e045d7c6-9fb5-4872-90f9-cbfad9ee646e",
              in = ParameterIn.QUERY
          )
      }
  )
  //@formatter:on
  @GetMapping("/tours/category")
  public PlotBundleDto getTourNumberStatsByCategory(@RequestParam(name = "categoryId", required = false) String categoryId,
                                                    @RequestParam(name = "recreationId", required = false) String recreationId) {

    final var filter = new TourismStatsFilter(categoryId);
    filter.setRecreationTypeIds(recreationId);

    return tourismStatsService.getTourNumberStats(filter);
  }

}
