package ru.rshb.intech.digital.service.controller.internship;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationResponse;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.InternshipRegistrationForm;
import ru.rshb.intech.digtial.commons.dto.FormValidationErrorResponse;


@Tag(
    name = "Стажировка",
    description = "API для операций, связанных со стажировкой"
)
@RequestMapping(
    path = "${api.prefix}/internship/registration",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class InternshipRegistrationController {

  private final InternshipRegistrationForm registrationForm;


  @Operation(
      summary = "Отправить данные о регистрации на стажировку.",
      description = "Принимает данные регистрации на стажировку.",
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
      requestBody = @RequestBody(description = "Данные формы ввода заявки на стажировку.")
  )
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public InternshipRegistrationResponse submitRegistration(@Validated @ModelAttribute InternshipRegistrationDto registrationRequest) {
    final var registration = registrationForm.process(registrationRequest);
    return new InternshipRegistrationResponse(registration.getEmail());
  }
}
