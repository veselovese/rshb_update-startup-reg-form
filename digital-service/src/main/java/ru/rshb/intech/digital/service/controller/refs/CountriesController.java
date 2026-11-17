package ru.rshb.intech.digital.service.controller.refs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feature.references.locations.api.City;
import ru.rshb.intech.digital.feature.references.locations.api.Country;
import ru.rshb.intech.digital.feature.references.locations.api.LocationService;

import java.util.List;

@Tag(
    name = "Справочники",
    description = "API справочников"
)
@RestController
@RequestMapping(
    path = "${api.prefix}/lookups/countries",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class CountriesController {

  private final LocationService locationService;

  @Operation(
      summary = "Найти страну по части названия",
      description = "Возвращает страну по части названия.",
      parameters = {
          @Parameter(
              name = "q",
              description = """
                            Часть названия страны (мин. 3 символа).
                            Если передано меньше 3-х символов - возвращает пустой список.
                            """,
              in = ParameterIn.QUERY
          )
      }
  )
  @GetMapping
  public List<Country> getCountry(@RequestParam("q") String countryTitle) {
    return locationService.findCountryByTitle(countryTitle);
  }

  @Operation(
      summary = "Найти город по стране и части названия",
      description = "Возвращает город по ID страны и части названия.",
      parameters = {
          @Parameter(
              name = "countryId",
              description = "ID страны. Целое число.",
              in = ParameterIn.PATH
          ),
          @Parameter(
              name = "q",
              description = """
                            Часть названия страны (мин. 3 символа).
                            Если передано меньше 3-х символов - возвращает пустой список.
                            """,
              in = ParameterIn.QUERY
          )
      }
  )
  @GetMapping("/{countryId}/cities")
  public List<City> getCityByCountry(@PathVariable Integer countryId,
                                     @RequestParam("q") String cityTitle) {

    return locationService.findByCountryIdAndCity(countryId, cityTitle);
  }

}
