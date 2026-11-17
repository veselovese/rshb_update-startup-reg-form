package ru.rshb.intech.digital.service.controller.internship;

import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFeedbackDto;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFeedbackLookupsDto;
import ru.rshb.intech.digital.feature.internship.core.service.feedback.form.InternshipFeedbackForm;
import ru.rshb.intech.digital.feature.internship.core.service.feedback.form.InternshipFeedbackLookupService;
import ru.rshb.intech.digtial.commons.dto.FormValidationErrorResponse;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

@Tag(
    name = "Стажировка",
    description = "API для операций, связанных со стажировкой"
)
@RequestMapping(
    path = "${api.prefix}/internship/feedback",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RestController
@RequiredArgsConstructor
public class InternshipFeedbackController {

  private final InternshipFeedbackForm feedbackForm;
  private final InternshipFeedbackLookupService feedbackLookupService;

  @Operation(
      summary = "Отправить данные обратной связи о стажировке.",
      description = "Принимает данные обратной связи о стажировке.",
      responses = {
          @ApiResponse(
              responseCode = "200",
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
      requestBody = @RequestBody(description = "Данные формы обратной связи о прохождении стажировки.")
  )
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public OperationResultResponse postFeedback(@Valid @ModelAttribute InternshipFeedbackDto formData) {
    feedbackForm.process(formData);
    return OperationResultResponse.localizedError("internship.feedback.request.accepted");
  }

  @Operation(
      summary = "Получить справочники для формы обратной связи по стажировкам.",
      description = "Возвращает справочники для формы обратной связи по стажировкам.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешное выполнение операции."
          )
      }
  )
  @GetMapping("/refs")
  public InternshipFeedbackLookupsDto getLookups() {
    return feedbackLookupService.getFeedbackFormLookups();
  }

}
