package ru.rshb.intech.digital.service.controller.feedback;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feedback.core.dto.FeedbackFormData;
import ru.rshb.intech.digital.feedback.core.service.report.form.FeedbackForm;
import ru.rshb.intech.digtial.commons.dto.FormValidationErrorResponse;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;


@Tag(
    name = "Обратная связь",
    description = ""
)
@RestController
@RequestMapping(
    path = "${api.prefix}/feedback",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class FeedbackController {

  private final FeedbackForm feedbackForm;


  @Operation(
      summary = "Отправить данные формы обратной связи",
      description = "Принимает данные, отправленные с формы обратной связи",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Данные приняты."
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Переданные данные невалидны.",
              content = @Content(schema = @Schema(implementation = FormValidationErrorResponse.class))
          )
      },
      requestBody = @RequestBody(description = "Данные формы ввода заявки.")
  )
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public OperationResultResponse postFeedback(@Validated @ModelAttribute FeedbackFormData feedback) {
    feedbackForm.process(feedback);
    return OperationResultResponse.localizedSuccess("feedback.submit.success");
  }
}
