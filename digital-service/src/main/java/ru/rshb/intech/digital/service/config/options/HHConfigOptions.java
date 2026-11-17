package ru.rshb.intech.digital.service.config.options;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class HHConfigOptions {

    @Value("${integrations.hh.user-agent}")
    private String userAgent;

    @Value("${integrations.hh.auth.client-id}")
    private String clientId;

    @Value("${integrations.hh.auth.client-secret}")
    private String clientSecret;

    @Value("${integrations.hh.auth.default-token}")
    private String defaultToken;

    @Value("${vacancies.collection.enabled}")
    private Boolean syncEnabled;
}
