package ru.rshb.intech.digital.service.controller.contests.patent;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feature.contests.patent.dto.PatentContestRegistrationLookupDto;
import ru.rshb.intech.digital.feature.contests.patent.dto.PatentContestRegistrationRequestDto;
import ru.rshb.intech.digital.feature.contests.patent.service.registration.form.PatentContestRegistrationForm;
import ru.rshb.intech.digital.feature.contests.patent.service.registration.lookup.PatentContestRegistrationLookupService;
import ru.rshb.intech.digital.references.cities.core.service.CityReferenceService;
import ru.rshb.intech.digital.references.cities.dto.CityDto;
import ru.rshb.intech.digtial.commons.dto.FormValidationErrorResponse;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

import java.util.List;

@Tag(
    name = "Патентный конкурс. Рыбное хозяйство",
    description = "API для конкурса потентов (рыбное хозяйство)"
)
@RestController
@RequestMapping(
    path = "${api.prefix}/contests/patent/fish",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class PatentContestController {

  private final PatentContestRegistrationForm registrationForm;
  private final PatentContestRegistrationLookupService lookupService;
  private final CityReferenceService cityReferenceService;

  @Operation(
      summary = "Отправить данные о регистрации на участие в конкурсе.",
      description = "Принимает данные регистрации на участие в конкурсе.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Данные для регистрации приняты."
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Переданные данные невалидны.",
              content = @Content(
                  schema = @Schema(
                      implementation = FormValidationErrorResponse.class
                  )
              )
          )
      },
      requestBody = @RequestBody(description = "Данные формы ввода заявки на участие в конкурсе.")
  )
  @PostMapping(
      path = "/registration",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  public OperationResultResponse postRegistration(@Valid @ModelAttribute PatentContestRegistrationRequestDto registrationDto) {
    registrationForm.process(registrationDto);
    return OperationResultResponse.localizedSuccess("common.info.message.application.accepted");
  }

  @Operation(
      summary = "Получить справочники для формы заявки на участие в конкурсе",
      description = """
                    Возвращает все справочники, необходимые для указания значений полей с фиксированными значениями.
                    """,
      requestBody = @RequestBody(description = "Справочники")
  )
  @GetMapping("/registration/refs")
  public PatentContestRegistrationLookupDto getLookups() {
    return lookupService.getLookups();
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
  @GetMapping("/registration/refs/cities")
  public List<CityDto> getCities(@RequestParam(name = "name") String nameTemplate) {
    return cityReferenceService.findByName(nameTemplate);
  }

}
