package ru.rshb.intech.digital.migration.posgres.config;


import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@AutoConfigureBefore(
    value = {LiquibaseAutoConfiguration.class}
)
public class PostgresMigrationConfig {

  @Bean
  public SpringLiquibase liquibase(@Qualifier("digitalDataSource") DataSource dataSource) {
    final var liquibase = new SpringLiquibase();
    liquibase.setDataSource(dataSource);
    liquibase.setChangeLog("classpath:/changelog/db.dao-master.xml");
    return liquibase;
  }
}
