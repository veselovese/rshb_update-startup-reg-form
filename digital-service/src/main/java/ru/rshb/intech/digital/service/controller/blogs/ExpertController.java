package ru.rshb.intech.digital.service.controller.blogs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationFormData;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertRegistrationRefDto;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.ExpertFieldService;
import ru.rshb.intech.digital.feature.blogs.core.experts.service.registration.form.ExpertRegistrationForm;
import ru.rshb.intech.digtial.commons.dto.FormValidationErrorResponse;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

@Tag(
    name = "Эксперты"
)
@RestController
@RequestMapping(
    path = "${api.prefix}/blogs/expert",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class ExpertController {

  private final ExpertRegistrationForm expertRegistrationForm;
  private final ExpertFieldService expertFieldService;

  @Operation(
      summary = "Отправить данные формы \"Стать экспертом\"",
      description = "Принимает данные заявки эксперта.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Данные заявки приняты."
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
      requestBody = @RequestBody(description = "Данные формы ввода заявки.")
  )
  @PostMapping(
      path = "/registration",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  public OperationResultResponse postExpertRegistration(@Validated @ModelAttribute ExpertRegistrationFormData registrationData) {
    expertRegistrationForm.process(registrationData);
    return OperationResultResponse.localizedSuccess("expert.registration.form.submit.success");
  }

  @Operation(
      summary = "Получить справочники для формы \"Стать экспертом\".",
      description = "Возвращает доступные справочники: Экспертная область блогера.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешное выполнение операции."
          )
      }
  )
  @GetMapping("/registration/refs")
  public ExpertRegistrationRefDto getExpertRegistrationRefs() {
    final var fields = expertFieldService.getExpertFields();
    return new ExpertRegistrationRefDto(fields);
  }
}
