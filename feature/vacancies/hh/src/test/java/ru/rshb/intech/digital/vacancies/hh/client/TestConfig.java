package ru.rshb.intech.digital.vacancies.hh.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TestConfig {

  @Bean("testRestTemplate")
  public RestTemplate restTemplate() {

    final var converter = new MappingJackson2HttpMessageConverter();
    converter.setSupportedMediaTypes(List.of(MediaType.ALL));

    final var converters = new ArrayList<HttpMessageConverter<?>>();
    converters.add(converter);

    final var restTemplate = new RestTemplate();
    restTemplate.setMessageConverters(converters);

    return restTemplate;
  }
}
