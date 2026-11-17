package ru.rshb.intech.digital.service.controller.internship;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feature.internship.core.dto.EducationStageRefDto;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFieldDto;
import ru.rshb.intech.digital.feature.internship.core.dto.WorkHoursDto;
import ru.rshb.intech.digital.feature.internship.core.service.education.EducationStageService;
import ru.rshb.intech.digital.feature.internship.core.service.fields.InternshipFieldService;
import ru.rshb.intech.digital.feature.internship.core.service.hours.WorkHoursService;
import ru.rshb.intech.digital.references.cities.core.service.CityReferenceService;
import ru.rshb.intech.digital.references.cities.dto.CityDto;
import ru.rshb.intech.digital.references.universities.core.service.UniversityReferenceService;
import ru.rshb.intech.digital.references.universities.dto.UniversityDto;

import java.util.List;

@Tag(
    name = "Стажировка",
    description = "API для операций, связанных со стажировкой"
)
@RequestMapping(
    path = "${api.prefix}/internship/registration/refs",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class InternshipRegistrationReferenceController {

  private final CityReferenceService cityReferenceService;
  private final UniversityReferenceService universityReferenceService;
  private final EducationStageService educationStageService;
  private final InternshipFieldService fieldService;
  private final WorkHoursService workHoursService;


  @Operation(
      summary = "Получить направления стажировок.",
      description = "Возвращает список доступных направлений стажировок.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешное выполнение операции."
          )
      }
  )
  @GetMapping(path = "/fields")
  public List<InternshipFieldDto> getAvailableInternshipFields() {
    return fieldService.findAvailableInternshipFields();
  }

  @Operation(
      summary = "Получить список населенных пунктов по критерию.",
      description = """
                    Возвращает список населенных пунктов, найденных в справочнике, по переданному критерию.
                    """,
      parameters = {
          @Parameter(
              name = "name",
              description = "Полное или частичное наименование населенного пункта."
          )
      }
  )
  @GetMapping("/cities")
  public List<CityDto> getCities(@RequestParam(name = "name") String nameTemplate) {
    return cityReferenceService.findByName(nameTemplate);
  }

  @Operation(
      summary = "Получить список учебных заведений по критерию.",
      description = """
                    Возвращает список учебных заведений, найденных в справочнике, по переданному критерию.
                    """,
      parameters = {
          @Parameter(
              name = "name",
              description = "Полное или частичное наименование учебного заведения."
          )
      }
  )
  @GetMapping("/education/universities")
  public List<UniversityDto> getUniversities(@RequestParam(name = "name") String nameTemplate) {
    return universityReferenceService.findByName(nameTemplate);
  }

  @Operation(
      summary = "Получить ступень обучения.",
      description = "Возвращает справочник с возможными вариантами ступеней обучения.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешное выполнение операции."
          )
      }
  )
  @GetMapping("/education/stages")
  public List<EducationStageRefDto> getEducationStagesRef() {
    return educationStageService.findEnabledStages();
  }

  @Operation(
      summary = "Получить список доступных значений для поля \"Количество рабочих часов\"",
      description = "Возвращает справочник с возможными значениями рабочих часов.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешное выполнение операции."
          )
      }
  )
  @GetMapping("/hours")
  public List<WorkHoursDto> getPreferredWorkHoursRef() {
    return workHoursService.findEnabledWorkHours();
  }
}
