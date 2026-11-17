package ru.rshb.intech.digital.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.rshb.intech.digital.service.config.utils.DigitalMessageSourceFactory;
import ru.rshb.intech.digital.service.config.utils.JsonUtils;
import ru.rshb.intech.digital.service.rate.RateLimitInterceptor;
import ru.rshb.intech.digital.service.rate.RateLimiterService;


@Configuration
@Profile({"default"})
public class WebConfig implements WebMvcConfigurer {

  private final RateLimiterService rateLimiterService;
  private final String apiPrefix;

  public WebConfig(RateLimiterService rateLimiterService, @Value("${api.prefix}") String apiPrefix) {
    this.rateLimiterService = rateLimiterService;
    this.apiPrefix = apiPrefix;
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    final var externalApi = String.format("/%s/**", apiPrefix);
    registry.addMapping(externalApi)
        .allowedOrigins("*")
        .allowedHeaders("*")
        .allowedMethods("*");
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(new RateLimitInterceptor(rateLimiterService))
        .addPathPatterns("/api/v1/**");
  }

  @Bean
  public ObjectMapper objectMapper() {
    return JsonUtils.defaultWebMapper();
  }

  @Override
  public Validator getValidator() {
    final var bean = new LocalValidatorFactoryBean();
    bean.setValidationMessageSource(messageSource());
    return bean;
  }

  @Bean
  public MessageSource messageSource() {
    return DigitalMessageSourceFactory.messageSource();
  }
}
