package ru.rshb.intech.digital.service.controller.innovations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feature.innovations.core.dto.GeneralRequestDto;
import ru.rshb.intech.digital.feature.innovations.core.service.general.form.GeneralRequestForm;
import ru.rshb.intech.digtial.commons.dto.FormValidationErrorResponse;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

import static ru.rshb.intech.digtial.commons.dto.OperationResultResponse.localizedSuccess;

@Tag(
    name = "Окно инноваций",
    description = "API для операций, связанных со инновациями."
)
@RequestMapping(
    path = "${api.prefix}/innovations",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RestController
@RequiredArgsConstructor
public class InnovationsController {

  private final GeneralRequestForm generalRequestForm;


  @Operation(
      summary = "Отправить данные с запросом на технологию или предложение продукта.",
      description = "Принимает с запросом на технологию или предложение продукта.",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Данные приняты."
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
      path = "/apply",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  @ResponseStatus(HttpStatus.CREATED)
  public OperationResultResponse postGeneralRequest(@Valid @ModelAttribute GeneralRequestDto innovationRequest) {
    generalRequestForm.process(innovationRequest);
    return localizedSuccess("innovations.submit.success");
  }
}
