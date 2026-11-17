package ru.rshb.intech.digital.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;
import ru.rshb.intech.digital.commons.forms.attributes.provider.ApplicationContextFormAttributeProvider;
import ru.rshb.intech.digital.commons.forms.attributes.provider.FormAttributeProvider;
import ru.rshb.intech.digital.service.config.options.HHConfigOptions;
import ru.rshb.intech.digital.subsystem.audit.AuditLog;
import ru.rshb.intech.digital.subsystem.audit.event.AuditLogEvent;
import ru.rshb.intech.digital.subsystem.audit.event.LogLevel;
import ru.rshb.intech.digital.subsystem.events.SubSystem;
import ru.rshb.intech.digital.vacancies.hh.client.HHClient;
import ru.rshb.intech.digital.vacancies.hh.client.HHClientOptions;
import ru.rshb.intech.digital.vacancies.hh.client.HHClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

@Slf4j
@EnableAsync(proxyTargetClass = true)
@EnableScheduling
@Configuration
@ComponentScan(basePackages = "ru.rshb.intech.digital")
@RequiredArgsConstructor
public class AppConfig implements AsyncConfigurer {

  private final AuditLog auditLog;


  @Override
  public Executor getAsyncExecutor() {
    final var executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(7);
    executor.setMaxPoolSize(42);
    executor.setQueueCapacity(11);
    executor.setThreadNamePrefix("digital-async-executor-");
    executor.initialize();
    return executor;
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return (e, method, params) -> log.error("Async execution error", e);
  }

  @Bean("hhClientRestTemplate")
  public RestTemplate restTemplate(ObjectMapper objectMapper) {
    final var converter = new MappingJackson2HttpMessageConverter(objectMapper);
    converter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON));

    final var converters = new ArrayList<HttpMessageConverter<?>>();
    converters.add(converter);

    final var restTemplate = new RestTemplate();
    restTemplate.setMessageConverters(converters);

    return restTemplate;
  }

  @Bean
  public HHClientOptions hhClientOptions(@Nonnull HHConfigOptions hhOptions) {
    return HHClientOptions.builder()
        .userAgent(hhOptions.getUserAgent())
        .clientId(hhOptions.getClientId())
        .clientSecret(hhOptions.getClientSecret())
        .defaultToken(hhOptions.getDefaultToken())
        .build();
  }

  @Bean
  @Scope("singleton")
  public HHClientService hhClientService(@Qualifier("hhClientRestTemplate") RestTemplate restTemplate,
                                         HHClientOptions clientOptions) {

    final var client = new HHClient(restTemplate, clientOptions);
    final var defaultToken = clientOptions.getDefaultToken();

    return StringUtils.isNotBlank(defaultToken)
        ? new HHClientService(client, defaultToken)
        : new HHClientService(client);
  }

  @Bean
  public TaskScheduler taskScheduler() {
    final var scheduler = new ThreadPoolTaskScheduler();
    scheduler.setPoolSize(5);
    scheduler.setErrorHandler((t) -> log.error("Task scheduler error", t));
    scheduler.setThreadNamePrefix("digital-scheduler-");
    return scheduler;
  }

  @Bean("applicationEventMulticaster")
  public ApplicationEventMulticaster mainApplicationEventMulticaster() {
    final var eventMulticaster = new SimpleApplicationEventMulticaster();
    eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
    return eventMulticaster;
  }

  @Bean
  public FormAttributeProvider formAttributeProvider(@Nonnull ApplicationContext applicationContext) {
    return new ApplicationContextFormAttributeProvider(applicationContext);
  }

  @PostConstruct
  public void handleAppStart() {
    auditLog.log(
        new AuditLogEvent(this, LogLevel.INFO, SubSystem.SERVICE)
            .withTitleLocalized("audit.event.system.start.title")
            .withDescriptionLocalized("audit.event.system.start.description")
    );
  }

  @PreDestroy
  public void handleAppExit() {
    auditLog.log(
        new AuditLogEvent(this, LogLevel.INFO, SubSystem.SERVICE)
            .withTitleLocalized("audit.event.system.stop.title")
            .withDescriptionLocalized("audit.event.system.stop.description")
    );
  }
}
