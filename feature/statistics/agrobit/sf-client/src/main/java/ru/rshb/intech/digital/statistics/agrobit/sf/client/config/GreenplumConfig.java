package ru.rshb.intech.digital.statistics.agrobit.sf.client.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.rshb.intech.digital.commons.utils.Persistence;

import javax.sql.DataSource;

@Configuration
public class GreenplumConfig {

  @Bean
  @Qualifier("agrobitDataSource")
  public DataSource agrobitDataSource(AgrobitGreenplumOptions dbOptions) {
    return DataSourceBuilder.create()
        .url(dbOptions.getUrl())
        .username(dbOptions.getUsername())
        .password(dbOptions.getPassword())
        .driverClassName(dbOptions.getDriverClass())
        .build();
  }

  @Bean
  @Qualifier(Persistence.GREENPLUM_STATS_JDBC_TEMPLATE_BEAN)
  public NamedParameterJdbcTemplate agrobitJdbcTemplate(@Qualifier("agrobitDataSource") DataSource dataSource) {
    return new NamedParameterJdbcTemplate(dataSource);
  }
}
