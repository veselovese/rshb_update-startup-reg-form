package ru.rshb.intech.digital.feedback.core;

import lombok.Generated;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Generated
@Component
public record FeedbackOptions(

    @Value("${request.limits.feedback.report.interval-hours}")
    Integer intervalHours,

    @Value("${request.limits.feedback.report.max-number}")
    Integer maxReportsCountPerInterval

) { }
