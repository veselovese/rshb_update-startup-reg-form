package ru.rshb.intech.digital.service.controller.subscriptions;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.subscriptions.core.mapper.SubscriptionCategoriesMapper;
import ru.rshb.intech.digital.subscriptions.core.mapper.SubscriptionFeedbackReportMapper;
import ru.rshb.intech.digital.subscriptions.core.mapper.SubscriptionRequestMapper;
import ru.rshb.intech.digital.subscriptions.core.service.SubscriptionService;
import ru.rshb.intech.digital.subscriptions.core.service.category.SubscriptionCategoryService;
import ru.rshb.intech.digital.subscriptions.dto.ActivationResult;
import ru.rshb.intech.digital.subscriptions.dto.ActivationStatus;
import ru.rshb.intech.digital.subscriptions.dto.SubscriptionCategoriesDto;
import ru.rshb.intech.digital.subscriptions.dto.SubscriptionRequest;
import ru.rshb.intech.digital.subscriptions.dto.UnsubscribeFeedbackDto;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

@Tag(
    name = "Подписки на новости и события",
    description = "API для функционала подписок пользователей на новости и события."
)
@RestController
@RequestMapping("${api.prefix}/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

  private final SubscriptionService subscriptionService;
  private final SubscriptionCategoryService subscriptionCategoryService;
  private final SubscriptionCategoriesMapper subscriptionCategoriesMapper;
  private final SubscriptionRequestMapper subscriptionRequestMapper;
  private final SubscriptionFeedbackReportMapper subscriptionFeedbackReportMapper;


  @Operation(
      summary = "Подписаться или переподписаться на рассылку",
      description = "Принимает заявку о подписке на рассылку.",
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          description = "Данные подписки."
      ),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Заявка на подписку обновлена.",
              content = @Content(schema = @Schema(implementation = OperationResultResponse.class))
          ),
          @ApiResponse(
              responseCode = "201",
              description = "Заявка на подписку создана.",
              content = @Content(schema = @Schema(implementation = OperationResultResponse.class))
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Данные запроса некорректны",
              content = @Content(schema = @Schema(implementation = OperationResultResponse.class))
          ),
      }
  )
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<OperationResultResponse> putSubscription(@RequestBody @Validated SubscriptionRequest request) {

    final var subscriptionData = subscriptionRequestMapper.toEntityFromDto(request);

    final var result = subscriptionService.subscribe(subscriptionData);

    return switch (result) {
      case CREATED -> ResponseEntity.status(HttpStatus.CREATED)
          .contentType(MediaType.APPLICATION_JSON)
          .body(OperationResultResponse.success(I18n.of("subscriptions.success.created")));
      case RENEWED -> ResponseEntity.status(HttpStatus.OK)
          .contentType(MediaType.APPLICATION_JSON)
          .body(OperationResultResponse.success(I18n.of("subscriptions.success.renewed")));
      default -> throw new UnsupportedOperationException("Not implemented yet");
    };
  }

  @Operation(
      summary = "Получить категории подписок",
      description = "Возвращает доступные категории для подписок",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешное выполнение операции"
          )
      }
  )
  @GetMapping(
      path = "/categories",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public SubscriptionCategoriesDto getSubscriptionCategories() {
    final var categories = subscriptionCategoryService.findAllCategories();
    return subscriptionCategoriesMapper.toDtoFromEntities(categories);
  }

  @Operation(
      summary = "Активировать подписку",
      description = "Проверяет активационный код и выполняет (или отклоняет) активацию.",
      parameters = {
          @Parameter(
              name = "code",
              description = "Код активации подписки."
          )
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Подписка активирована."
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Данные запроса некорректны",
              content = @Content(schema = @Schema(implementation = OperationResultResponse.class))
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Заявка на подписку с указанным кодом активации не найдена",
              content = @Content(schema = @Schema(implementation = OperationResultResponse.class))
          ),
      }
  )
  @PostMapping(
      path = "/activation",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.OK)
  public ActivationResult activateSubscription(@RequestParam(value = "code") String activationCode) {
    subscriptionService.activateSubscription(activationCode);
    return new ActivationResult(ActivationStatus.SUBSCRIBED);
  }

  @Operation(
      summary = "Отписаться от подписки",
      description = "Проверяет деактивационный код и выполняет отписку.",
      parameters = {
          @Parameter(
              name = "code",
              description = "Код деактивации подписки."
          )
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Подписка деактивирована."
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Данные запроса некорректны",
              content = @Content(schema = @Schema(implementation = OperationResultResponse.class))
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Подписка с указанным кодом деактивации не найдена",
              content = @Content(schema = @Schema(implementation = OperationResultResponse.class))
          ),
      }
  )
  @PostMapping(
      path = "/deactivation",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ActivationResult unsubscribe(@RequestParam("code") String deactivationCode,
                                      @RequestBody UnsubscribeFeedbackDto feedbackDto) {
    final var feedbackReport = subscriptionFeedbackReportMapper.toEntityFromDto(feedbackDto);
    subscriptionService.unsubscribe(deactivationCode, feedbackReport);
    return new ActivationResult(ActivationStatus.UNSUBSCRIBED);
  }
}