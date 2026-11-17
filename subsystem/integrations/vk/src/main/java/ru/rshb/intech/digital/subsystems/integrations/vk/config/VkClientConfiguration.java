package ru.rshb.intech.digital.subsystems.integrations.vk.config;

import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import ru.rshb.intech.digital.subsystem.audit.event.AuditLogEvent;
import ru.rshb.intech.digital.subsystem.audit.event.LogLevel;
import ru.rshb.intech.digital.subsystem.events.AppEventBus;
import ru.rshb.intech.digital.subsystem.events.SubSystem;
import ru.rshb.intech.digital.subsystems.integrations.vk.exception.VkIntegrationException;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class VkClientConfiguration {

  private final AppEventBus appEventBus;

  @Bean
  public VkApiClient vkApiClient() {
    final var transportClient = new HttpTransportClient();
    return new VkApiClient(transportClient);
  }

  @Lazy
  @Bean
  @Primary
  @Profile({"default", "dev-stand"})
  public VkAuth vkAuth(@Nonnull VkApiClient vkApiClient, @Nonnull VkClientOptions vkClientOptions) {

    log.debug("Authenticating in VK...");

    try {

      final var authResponse = vkApiClient.oAuth()
          .serviceClientCredentialsFlow(vkClientOptions.getAppId(), vkClientOptions.getSecureKey())
          .lang(Lang.RU)
          .execute();

      log.info("VK auth: SUCCESS");

      return new VkAuth(vkClientOptions.getAppId(), authResponse.getAccessToken());
    } catch (Exception e) {

      log.error("VK auth: ERROR", e);

      appEventBus.log(
          new AuditLogEvent(this, LogLevel.ERROR, SubSystem.VK)
              .withTitleLocalized("audit.event.integrations.vk.auth.error.title")
              .withDescriptionLocalized("audit.event.integrations.vk.auth.error.description")
              .withThrowable(e)
      );

      throw new VkIntegrationException("VK auth error", e);
    }
  }

  @Bean
  @Profile("dev-local")
  public VkAuth disabledVkAuth() {
    log.info("VK auth: DISABLED");
    return VkAuth.createDisabled();
  }

}
