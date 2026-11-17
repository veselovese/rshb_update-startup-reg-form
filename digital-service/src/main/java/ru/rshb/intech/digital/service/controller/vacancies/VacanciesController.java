package ru.rshb.intech.digital.service.controller.vacancies;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.vacancies.core.service.VacancyService;
import ru.rshb.intech.digital.vacancies.core.service.candidate.form.VacancyCandidateForm;
import ru.rshb.intech.digital.vacancies.hh.dto.filter.VacancyFilterLookupDto;
import ru.rshb.intech.digital.vacancies.model.CandidateCvDto;
import ru.rshb.intech.digital.vacancies.model.VacanciesFilter;
import ru.rshb.intech.digital.vacancies.model.VacancyDto;
import ru.rshb.intech.digital.vacancies.model.VacancyHighlightDto;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;
import ru.rshb.intech.digtial.commons.dto.Page;

@Tag(
    name = "Вакансии",
    description = "API для функционала вакансий."
)
@CrossOrigin
@RestController
@RequestMapping(
    path = "${api.prefix}/vacancies",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class VacanciesController {

  private final VacancyService vacancyService;
  private final VacancyCandidateForm vacancyCandidateForm;


  @Operation(
      summary = "Получить все активные вакансии.",
      description = "Возвращает список активных вакансий.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешное выполнение операции."
          )
      },
      parameters = {
          @Parameter(
              name = VacanciesFilter.SORT,
              description = "Сортировка",
              example = "date|match"
          ),
          @Parameter(
              name = VacanciesFilter.COMPANIES,
              description = "Внешний ID компании (см. запрос с возможными значениями фильтров).",
              example = "3131901,58320"
          ),
          @Parameter(
              name = VacanciesFilter.FIELDS,
              description = "Внешний ID специализации (см. запрос с возможными значениями фильтров).",
              example = "1.270,2.33"
          ),
          @Parameter(
              name = VacanciesFilter.EMPLOYMENTS,
              description = "Внешний ID типа занятости (см. запрос с возможными значениями фильтров).",
              example = "full,probation"
          ),
          @Parameter(
              name = VacanciesFilter.EXPERIENCES,
              description = "Внешний ID варианта опыта работы (см. запрос с возможными значениями фильтров).",
              example = "between1And3,between3And6"
          ),
          @Parameter(
              name = VacanciesFilter.CITY,
              description = "Внешний ID города (см. запрос с возможными значениями фильтров).",
              example = "53"
          ),
          @Parameter(
              name = "page",
              description = "Номер страницы. 0-based. Если параметр отсутствует - 0",
              example = "0"
          ),
          @Parameter(
              name = "size",
              description = "Требуемое кол-во элементов на страницу. Если параметр отсутствует - 10",
              example = "10"
          )
      }
  )
  @GetMapping
  public Page<VacancyHighlightDto> getActiveVacancies(@RequestParam(name = VacanciesFilter.SORT, required = false) String sort,
                                                      @RequestParam(name = VacanciesFilter.COMPANIES, required = false) String companies,
                                                      @RequestParam(name = VacanciesFilter.FIELDS, required = false) String fields,
                                                      @RequestParam(name = VacanciesFilter.EMPLOYMENTS, required = false) String employment,
                                                      @RequestParam(name = VacanciesFilter.EXPERIENCES, required = false) String experience,
                                                      @RequestParam(name = VacanciesFilter.CITY, required = false) String city,
                                                      @RequestParam(required = false, defaultValue = "0") Integer page,
                                                      @RequestParam(required = false, defaultValue = "10") Integer size) {
    return vacancyService.findAll(
        VacanciesFilter.builder()
            .sort(sort)
            .companies(companies)
            .fields(fields)
            .employment(employment)
            .experience(experience)
            .city(city)
            .build(),
        PageRequest.of(page, size)
    );
  }

  @Operation(
      summary = "Получить вакансию по ID",
      description = "Возвращает вакансию по ее ID в HH.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешное выполнение операции."
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Вакансия с указанным ID не найдена",
              content = @Content(
                  schema = @Schema(implementation = OperationResultResponse.class)
              )
          )
      },
      parameters = {
          @Parameter(
              name = "externalId",
              description = "ID вакансии в HH",
              example = "70909077",
              in = ParameterIn.PATH
          )
      }
  )
  @GetMapping("/{externalId}")
  public VacancyDto getVacancy(@PathVariable String externalId) {
    return vacancyService.getByExternalId(externalId);
  }

  @Operation(
      summary = "Получить все фильтры вакансий",
      description = "Возвращает все фильтры вакансий со всеми возможными значениями.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешное выполнение операции."
          )
      }
  )
  @GetMapping(
      path = "/filter",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public VacancyFilterLookupDto getFilters() {
    return vacancyService.collectFilters();
  }

  @Operation(
      summary = "Отправить данные соискателя.",
      description = "Принимает данные соискателя.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешное выполнение операции."
          )
      }
  )
  @PostMapping(
      path = "/candidate/resume",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  public OperationResultResponse postCandidateResume(@Valid @ModelAttribute CandidateCvDto formData) {
    vacancyCandidateForm.process(formData);
    return OperationResultResponse.localizedSuccess("common.info.message.application.accepted");
  }
}
