package ru.rshb.intech.digital.subsystems.integrations.vk.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class VkClientOptions {

  private final Integer appId;
  private final String secureKey;
  private final String serviceToken;

  public VkClientOptions(@Value("${integrations.vk.auth.app-id}") Integer appId,
                         @Value("${integrations.vk.auth.secure-key}") String secureKey,
                         @Value("${integrations.vk.auth.service-token") String serviceToken) {
    this.appId = appId;
    this.secureKey = secureKey;
    this.serviceToken = serviceToken;
  }
}
