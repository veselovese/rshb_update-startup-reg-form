package ru.rshb.intech.digital.content.provider.elasticsearch.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import static org.apache.commons.lang3.StringUtils.isNotBlank;


@Configuration
@RequiredArgsConstructor
@EnableElasticsearchRepositories(
    basePackages = "ru.rshb.intech.digital.content.provider.elasticsearch.repository"
)
public class ElasticsearchContentProviderConfig extends ElasticsearchConfiguration {

  private final ElasticsearchOptions options;

  @Nonnull
  @Override
  public ClientConfiguration clientConfiguration() {
    return isNotBlank(options.getUsername()) || isNotBlank(options.getPassword())
        ? basicAuthConfig()
        : noAuthConfig();
  }

  @Nonnull
  private ClientConfiguration noAuthConfig() {
    return ClientConfiguration.builder()
        .connectedTo(options.getUrl())
        .build();
  }

  @Nonnull
  private ClientConfiguration basicAuthConfig() {
    return ClientConfiguration.builder()
        .connectedTo(options.getUrl())
        .withBasicAuth(options.getUsername(), options.getPassword())
        .build();
  }

  @Bean
  @Lazy
  public ElasticsearchOperations elasticsearchOperations(ElasticsearchClient client) {
    return new ElasticsearchTemplate(client);
  }
}
