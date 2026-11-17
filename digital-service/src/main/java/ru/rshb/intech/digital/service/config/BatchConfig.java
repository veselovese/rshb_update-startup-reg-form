package ru.rshb.intech.digital.service.config;

import jakarta.annotation.Nonnull;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig extends DefaultBatchConfiguration {

  private final String tablePrefix;
  private final PlatformTransactionManager txManager;
  private final DataSource dataSource;

  public BatchConfig(@Value("${spring.batch.jdbc.table-prefix}") String tablePrefix,
                     @Qualifier("digitalDataSource") DataSource dataSource) {

    this.tablePrefix = tablePrefix;
    this.txManager = new JdbcTransactionManager(dataSource);
    this.dataSource = dataSource;
  }

  @Nonnull
  @Override
  protected PlatformTransactionManager getTransactionManager() {
    return txManager;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    return txManager;
  }

  @Nonnull
  @Override
  protected String getTablePrefix() {
    return tablePrefix;
  }

  @Nonnull
  @Override
  protected DataSource getDataSource() {
    return dataSource;
  }
}
