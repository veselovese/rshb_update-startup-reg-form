package ru.rshb.intech.digital.service.controller.startups;

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
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feature.startups.core.service.registration.StartupRegistrationLookupService;
import ru.rshb.intech.digital.feature.startups.core.service.registration.form.StartupRegistrationForm;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegLookupDto;
import ru.rshb.intech.digital.feature.startups.dto.StartupRegistrationDto;
import ru.rshb.intech.digtial.commons.dto.FormValidationErrorResponse;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

import static ru.rshb.intech.digtial.commons.dto.OperationResultResponse.localizedSuccess;

@Tag(
    name = "Стартапы",
    description = "API стартапов."
)
@RestController
@RequestMapping(
    path = "${api.prefix}/startups/registration",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class StartupRegistrationController {

  private final StartupRegistrationForm startupRegistrationForm;
  private final StartupRegistrationLookupService lookupService;

  @Operation(
      summary = "Отправить данные формы заявки на включение стартапа в витрину.",
      description = "Принимает данные заявки на включение стартапа.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Заявка на включение принята."
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
      requestBody = @RequestBody(description = "Данные формы")
  )
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Transactional
  public OperationResultResponse postRegistration(@Valid @ModelAttribute StartupRegistrationDto formData) {
    return processRegistration(formData);
  }

  private OperationResultResponse processRegistration(StartupRegistrationDto formData) {
    startupRegistrationForm.process(formData);
    return localizedSuccess("common.info.message.application.accepted");
  }

  @Operation(
      summary = "Получить справочники для формы заявки на включение стартапа в витрину",
      description = """
                    Возвращает все справочники, необходимые для указания значений полей с фиксированными значениями.
                    """,
      requestBody = @RequestBody(description = "Справочники")
  )
  @GetMapping( "/refs")
  public StartupRegLookupDto getStartupRegistrationRefs() {
    return lookupService.getRegistrationLookups();
  }
}
