package ru.rshb.intech.digital.service.controller.subscriptions;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rshb.intech.digital.subscriptions.core.exception.SubscriptionNotFoundException;
import ru.rshb.intech.digital.subscriptions.core.exception.SubscriptionValidationException;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

@RestControllerAdvice(
        assignableTypes = SubscriptionController.class
)
@Order(1)
public class SubscriptionExceptionHandler {

    @Hidden
    @ApiResponse(
            responseCode = "400",
            description = "Данные запроса некорректны",
            content = @Content
    )
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SubscriptionValidationException.class)
    public OperationResultResponse handleSubscriptionValidationException(SubscriptionValidationException e) {
        return OperationResultResponse.error(e.getMessage());
    }

    @Hidden
    @ApiResponse(
            responseCode = "404",
            description = "Ресурс не найден",
            content = @Content
    )
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SubscriptionNotFoundException.class)
    public OperationResultResponse handleSubscriptionNotFoundException(SubscriptionNotFoundException e) {
        return OperationResultResponse.error(e.getMessage());
    }
}
