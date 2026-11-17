package ru.rshb.intech.digital.service.rate.exception;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import ru.rshb.intech.digital.commons.utils.exceptiion.DigitalServiceException;

@Getter
public class RateLimitExceededException extends DigitalServiceException {

    private static final String MSG_TEMPLATE = "Rate limit exceeded: sessionId=[%s], resource=[%s]";

    private final String sessionId;
    private final String resource;


    public RateLimitExceededException(@Nonnull String sessionId, @Nonnull String resource) {
        super(String.format(MSG_TEMPLATE, sessionId, resource));

        this.sessionId = sessionId;
        this.resource = resource;
    }
}
