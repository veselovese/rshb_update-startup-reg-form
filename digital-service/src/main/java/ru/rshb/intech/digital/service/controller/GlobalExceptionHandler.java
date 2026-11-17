package ru.rshb.intech.digital.service.controller;

import com.google.common.collect.ArrayListMultimap;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeMaxLengthExceededException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValidationException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.commons.utils.ErrorType;
import ru.rshb.intech.digital.commons.utils.attachment.AttachmentException;
import ru.rshb.intech.digital.feature.downloads.core.exception.RequestedFileNotFound;
import ru.rshb.intech.digital.service.rate.exception.RateLimitExceededException;
import ru.rshb.intech.digital.subsystem.audit.event.AuditLogEvent;
import ru.rshb.intech.digital.subsystem.audit.event.LogLevel;
import ru.rshb.intech.digital.subsystem.events.AppEventBus;
import ru.rshb.intech.digital.subsystem.events.SubSystem;
import ru.rshb.intech.digtial.commons.dto.FormValidationErrorResponse;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

import java.util.stream.Collectors;

import static ru.rshb.intech.digital.commons.utils.ErrorType.INCORRECT_REQUEST;
import static ru.rshb.intech.digital.commons.utils.ErrorType.UNEXPECTED_ERROR;
import static ru.rshb.intech.digital.commons.utils.ErrorType.VALIDATION_ERROR;
import static ru.rshb.intech.digtial.commons.dto.OperationResultResponse.localizedError;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final AppEventBus appEventBus;


  @ApiResponse(
      responseCode = "429",
      description = "Превышена максимально допустимая частота запросов к данному ресурсу от одного пользователя."
  )
  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
  @ExceptionHandler(RateLimitExceededException.class)
  public ResponseEntity<OperationResultResponse> handleRateLimitExceededException(@Nonnull RateLimitExceededException e) {

    final var errorMessage = ErrorType.RATE_LIMIT_EXCEEDED.getTitle();
    log.error(errorMessage, e);

    appEventBus.log(
        new AuditLogEvent(this, LogLevel.ERROR, SubSystem.WEB)
            .withTitleLocalized("audit.event.web.rate.limit.error.title")
            .withDescription(errorMessage)
            .withThrowable(e)
    );

    return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
        .body(
            FormValidationErrorResponse.error(
                ErrorType.RATE_LIMIT_EXCEEDED.getTitle()
            )
        );
  }

  @ApiResponse(
      responseCode = "500",
      description = "Внутренняя ошибка сервера"
  )
  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ResponseEntity<OperationResultResponse> handleException(@Nonnull Exception e) {

    final var errorMessage = UNEXPECTED_ERROR.getTitle();
    log.error(errorMessage, e);

    appEventBus.log(
        new AuditLogEvent(this, LogLevel.ERROR, SubSystem.WEB)
            .withTitle(errorMessage)
            .withDescriptionLocalized("audit.event.web.internal.error.description")
            .withThrowable(e)
    );

    return new ResponseEntity<>(
        OperationResultResponse.error(errorMessage),
        HttpStatus.INTERNAL_SERVER_ERROR
    );
  }

  /**
   * Обработка нарушения валидации.
   *
   * @param e Исключение.
   * @return Массив ключ-значение, где ключ - наименование поля, значение - ошибка о нарушении ограничений.
   */
  @Hidden
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BindException.class)
  public ResponseEntity<FormValidationErrorResponse> handleValidationExceptions(@Nonnull BindException e) {

    log.error(VALIDATION_ERROR.getTitle(), e);

    final var fieldErrors = e.getBindingResult()
        .getAllErrors()
        .stream()
        .map(err -> (FieldError) err)
        .toList();

    final var validationResults = ArrayListMultimap.<String, String>create();

    for (final var fieldError : fieldErrors) {
      final var field = fieldError.getField();
      final var message = fieldError.getDefaultMessage();
      validationResults.put(field, message);
    }

    final var errors = validationResults.asMap()
        .entrySet()
        .stream()
        .collect(
            Collectors.toMap(
                entry -> entry.getKey(),
                entry -> entry.getValue()
                    .stream()
                    .sorted()
                    .findFirst()
                    .orElse(StringUtils.EMPTY)
            )
        );

    return ResponseEntity.badRequest()
        .body(new FormValidationErrorResponse(VALIDATION_ERROR.getTitle(), errors));
  }

  @Hidden
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(AttachmentException.class)
  public ResponseEntity<OperationResultResponse> handleAttachmentError(@Nonnull AttachmentException e) {
    log.error("Attachment error", e);
    return ResponseEntity.badRequest()
        .body(
            OperationResultResponse.error(e.getMessage())
        );
  }

  @Hidden
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(
      value = {
          AttributeValidationException.class,
          AttributeValueMissingException.class,
          AttributeBadFormatException.class,
          AttributeBusinessRulesException.class,
          AttributeMaxLengthExceededException.class
      }
  )
  public ResponseEntity<OperationResultResponse> handleAttributeValidationException(@Nonnull AttributeValidationException e) {
    log.error("Attribute validation error", e);
    return ResponseEntity.badRequest()
        .body(
            OperationResultResponse.error(e.getMessage())
        );
  }

  @Hidden
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(
      value = {
          RequestedFileNotFound.class
      }
  )
  public ResponseEntity<OperationResultResponse> handleNotFoundException(@Nonnull Exception e) {
    log.error("Resource not found", e);
    return ResponseEntity.notFound()
        .build();
  }

  @Hidden
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(
      value = {
          MissingServletRequestParameterException.class,
          HttpMessageNotReadableException.class
      }
  )
  public ResponseEntity<OperationResultResponse> handleValidationException(@Nonnull Exception e) {

    log.error(INCORRECT_REQUEST.getTitle(), e);

    return ResponseEntity.badRequest()
        .body(
            FormValidationErrorResponse.error(INCORRECT_REQUEST.getTitle())
        );
  }

  @Hidden
  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<OperationResultResponse> handleUploadSizeLimitExceeded(HttpServletRequest request, MaxUploadSizeExceededException e) {
    log.error("Max upload size exceeded for [{} {}]", request.getMethod(), request.getRequestURI(), e);
    return ResponseEntity.badRequest()
        .body(localizedError("common.error.file.max.size.exceeded"));
  }

  @Hidden
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<OperationResultResponse> handleMediaTypeNotSupported(HttpServletRequest request, HttpMediaTypeNotSupportedException e) {
    log.error("Content-Type [{}] not supported for request [{} {}]", e.getContentType(), request.getMethod(), request.getRequestURI());
    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
        .body(localizedError("common.error.invalid.content.type"));
  }

  @Hidden
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<OperationResultResponse> handleMethodNotSupported(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
    log.error("Method [{}] not allowed for URI [{}]", e.getMethod(), request.getRequestURI());
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        .body(localizedError("common.error.invalid.unsupported.method"));
  }
}
