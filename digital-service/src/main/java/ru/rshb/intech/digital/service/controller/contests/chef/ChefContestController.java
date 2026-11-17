package ru.rshb.intech.digital.service.controller.contests.chef;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feature.contests.chef.dto.ChefContestUrlCheckRequest;
import ru.rshb.intech.digital.feature.contests.chef.dto.ChefContestUrlCheckResult;
import ru.rshb.intech.digital.feature.contests.chef.dto.ChefContestantFormData;
import ru.rshb.intech.digital.feature.contests.chef.service.ChefContestService;
import ru.rshb.intech.digital.feature.contests.chef.service.contestant.form.ChefContestantForm;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

@Tag(
    name = "Конкурс \"Повар\"",
    description = "API функционала для конкурса \"Повар\""
)
@RestController
@RequestMapping(
    path = "${api.prefix}/contest/chef",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class ChefContestController {

  private final ChefContestService chefContestService;
  private final ChefContestantForm chefContestantForm;


  @Operation(
      summary = "Проверить URL решения участника",
      description = "Возвращает результат проверки переданного URL.",
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          description = "Данные для проверки"
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
              description = "Проверяемый URL недоступен или не возвращает неверные данные",
              content = @Content(schema = @Schema(implementation = OperationResultResponse.class))
          )
      }
  )
  @PostMapping(
      path = "/url",
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  public ChefContestUrlCheckResult postUrlCheck(@RequestBody ChefContestUrlCheckRequest urlCheckData) {
    final long urlLevel = chefContestService.getCheckLevel(urlCheckData.getUrl());
    return new ChefContestUrlCheckResult(urlLevel);
  }

  @Operation(
      summary = "Отправить данные участника конкурса",
      description = "Принимает данные формы участника конкурса.",
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
      path = "/contestant",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  public OperationResultResponse postContestantForm(@Valid @ModelAttribute ChefContestantFormData contestantData) {
    chefContestantForm.process(contestantData);
    return OperationResultResponse.success();
  }

}
