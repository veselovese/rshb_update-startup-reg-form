package ru.rshb.intech.digital.service.controller.contests.olympiad;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.AgrotechOlympiadQuestionDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.AgrotechOlympiadRegLookupsDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.AgrotechOlympiadRegistrationDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.service.form.AgrotechOlympiadQuestionForm;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.AgrotechOlympiadRegistrationLookupService;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service.form.AgrotechOlympiadRegistrationForm;
import ru.rshb.intech.digital.references.universities.dto.UniversityDto;
import ru.rshb.intech.digtial.commons.dto.FormValidationErrorResponse;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

import java.util.List;

import static ru.rshb.intech.digtial.commons.dto.OperationResultResponse.localizedSuccess;

@Tag(
    name = "Агротех олимпиада среди школьников",
    description = "API"
)
@RestController
@RequestMapping(
    path = "${api.prefix}/agrotech",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class AgrotechOlympiadController {

  private final AgrotechOlympiadQuestionForm questionForm;
  private final AgrotechOlympiadRegistrationForm registrationForm;
  private final AgrotechOlympiadRegistrationLookupService lookupService;

  @Operation(
      summary = "Отправить вопрос об олимпиаде.",
      description = "Принимает данные с формы вопроса.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Вопрос принят."
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
      requestBody = @RequestBody(description = "Данные формы ввода вопроса об олимпиаде.")
  )
  @PostMapping(
      path = "/olympiad/question",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  public OperationResultResponse postQuestion(@Valid @ModelAttribute AgrotechOlympiadQuestionDto formData) {
    questionForm.process(formData);
    return localizedSuccess("agrotech.olympiad.question.form.submit.success");
  }

  @Operation(
      summary = "Отправить данные регистрации в конкурсе \"Навстречу Агротеху\".",
      description = "Принимает данные с регистрации на конкурс.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Заявка на регистрацию принята."
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
      requestBody = @RequestBody(description = "Данные формы регистрации на участие в олимпиаде.")
  )
  @PostMapping(
      path = "/olympiad/registration",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  @Transactional
  public OperationResultResponse postRegistration(@Valid @ModelAttribute AgrotechOlympiadRegistrationDto formData) {
    registrationForm.process(formData);
    return localizedSuccess("common.info.message.application.accepted");
  }


  @Operation(
      summary = "Получить справочники для формы заявки на участие в олимпиаде \"Навстречу Агротеху\"",
      description = """
                    Возвращает все справочники, необходимые для указания значений полей с фиксированными значениями.
                    """,
      requestBody = @RequestBody(description = "Справочники")
  )
  @GetMapping("/olympiad/registration/refs")
  public AgrotechOlympiadRegLookupsDto getRegistrationRefs() {
    return lookupService.getFixedLookups();
  }

  @Operation(
      summary = "Справочник ВУЗов",
      description = """
                    Возвращает результат поиска в справочнике ВУЗов, участвующих в конкурсе.
                    """,
      requestBody = @RequestBody(description = "ВУЗы")
  )
  @GetMapping("/olympiad/registration/refs/university")
  public List<UniversityDto> getRegistrationUniversityRef(@RequestParam String title) {
    return lookupService.findUniversitiesByTitle(title);
  }
}
