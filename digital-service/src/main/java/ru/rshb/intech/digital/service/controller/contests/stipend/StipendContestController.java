package ru.rshb.intech.digital.service.controller.contests.stipend;

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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.subsystem.contests.stipend.dto.StipendContestQuestionFormData;
import ru.rshb.intech.digital.subsystem.contests.stipend.dto.StipendContestSolutionFormData;
import ru.rshb.intech.digital.subsystem.contests.stipend.form.question.StipendContestQuestionForm;
import ru.rshb.intech.digital.subsystem.contests.stipend.form.solution.StipendContestTaskSolutionForm;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

@Tag(
    name = "Стипендиальный конкурс",
    description = "API функционала для стипендиального конкурса"
)
@RestController
@RequestMapping(
    path = "${api.prefix}/contest/stipend",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class StipendContestController {

  private final StipendContestQuestionForm questionForm;
  private final StipendContestTaskSolutionForm solutionForm;

  @Operation(
      summary = "Отправить вопрос по стипендиальному конкурсу",
      description = "Принимает данные формы с вопросом по конкурсу.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешное выполнение операции"
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Данные запроса некорректны",
              content = @Content(schema = @Schema(implementation = OperationResultResponse.class))
          )
      }
  )
  @PostMapping(
      path = "/question",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  public OperationResultResponse postQuestion(@Valid @ModelAttribute StipendContestQuestionFormData questionFormData) {
    questionForm.process(questionFormData);
    return OperationResultResponse.success();
  }

  @Operation(
      summary = "Отправить решение по стипендиальному конкурсу",
      description = "Принимает данные формы с решением по заданию конкурса.",
      parameters = {
          @Parameter(
              name = "taskId",
              description = "Идентификатор задачи.",
              required = true
          )
      },
      requestBody = @RequestBody(
          description = "Данные формы с решением задания."
      ),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешное выполнение операции"
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Данные запроса некорректны",
              content = @Content(schema = @Schema(implementation = OperationResultResponse.class))
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Задача с указанным taskId не найдена",
              content = @Content(schema = @Schema(implementation = OperationResultResponse.class))
          )
      }
  )
  @PostMapping(
      path = "/solution/{taskId}",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  public OperationResultResponse postTaskSolution(@PathVariable("taskId") String taskId,
                                                  @Validated @ModelAttribute StipendContestSolutionFormData solutionFormData) {

    solutionFormData.setTaskId(taskId);
    solutionForm.process(solutionFormData);
    return OperationResultResponse.success();
  }

}
