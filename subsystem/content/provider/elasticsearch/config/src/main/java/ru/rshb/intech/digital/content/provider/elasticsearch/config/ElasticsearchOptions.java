package ru.rshb.intech.digital.content.provider.elasticsearch.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.Nonnull;

@Getter
@Component
public class ElasticsearchOptions {

  @Value("${content.provider.elasticsearch.username}")
  private String username;

  @Value("${content.provider.elasticsearch.password}")
  private String password;

  @Value("${content.provider.elasticsearch.host}")
  private String host;

  @Value("${content.provider.elasticsearch.port}")
  private Integer port;

  @Nonnull
  public String getUrl() {
    return String.format("%s:%s", host, port);
  }
}
