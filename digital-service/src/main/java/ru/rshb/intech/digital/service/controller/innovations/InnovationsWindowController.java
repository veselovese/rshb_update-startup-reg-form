package ru.rshb.intech.digital.service.controller.innovations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowDemandFormDto;
import ru.rshb.intech.digital.feature.innovations.core.dto.InnovationWindowProposalFormDto;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.WindowDemandService;
import ru.rshb.intech.digital.feature.innovations.core.service.window.demand.form.WindowDemandForm;
import ru.rshb.intech.digital.feature.innovations.core.service.window.proposal.form.WindowProposalForm;
import ru.rshb.intech.digtial.commons.dto.FormValidationErrorResponse;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

import java.util.Map;
import java.util.Set;

import static ru.rshb.intech.digtial.commons.dto.OperationResultResponse.localizedSuccess;

@Tag(
    name = "Окно инноваций",
    description = "API для операций, связанных со окном инноваций."
)
@RequestMapping(
    path = "${api.prefix}/innovations/window/demand",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RestController
@RequiredArgsConstructor
public class InnovationsWindowController {

  private final WindowDemandService windowDemandService;
  private final WindowDemandForm windowDemandForm;
  private final WindowProposalForm windowProposalForm;


  @Operation(
      summary = "Отправить данные с запросом на технологию.",
      description = "Принимает запрос на технологию",
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
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  @ResponseStatus(HttpStatus.CREATED)
  public OperationResultResponse postDemandForm(@Validated @ModelAttribute InnovationWindowDemandFormDto demandRequest) {
    windowDemandForm.process(demandRequest);
    return localizedSuccess("innovations.submit.success");
  }

  @Operation(
      summary = "Отправить данные с откликом на запрос на технологию.",
      description = "Принимает отклик на запрос на технологию",
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
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Заявка с указанным ID не найдена.",
              content = @Content(
                  schema = @Schema(
                      implementation = OperationResultResponse.class
                  )
              )
          )
      },
      requestBody = @RequestBody(description = "Данные формы ввода заявки.")
  )
  @PostMapping(
      path = "/{demandId}/proposal",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  @ResponseStatus(HttpStatus.CREATED)
  public OperationResultResponse postProposalForm(@PathVariable String demandId,
                                                  @Validated @ModelAttribute InnovationWindowProposalFormDto proposalForm) {

    proposalForm.setDemandCardId(demandId);
    windowProposalForm.process(proposalForm);
    return localizedSuccess("innovations.submit.success");
  }

  @Operation(
      summary = "Получить количество откликов",
      description = "Возвращает количество откликов по ID запросов на технологии",
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
      parameters = {
          @Parameter(
              name = "demandId",
              description = "ID запросов на технологию, по которым требуется получить количество откликов",
              required = true
          )
      }
  )
  @GetMapping(path = "/proposal/count")
  public Map<String, Long> getProposalsCount(@RequestParam("demandId") @NotEmpty Set<String> demandIds) {
    return windowDemandService.proposalCounts(demandIds);
  }
}
