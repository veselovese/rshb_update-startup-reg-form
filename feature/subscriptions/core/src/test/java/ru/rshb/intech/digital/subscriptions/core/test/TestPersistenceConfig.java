package ru.rshb.intech.digital.subscriptions.core.test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@TestConfiguration
@EnableJpaRepositories(
    basePackages = {
        "ru.rshb.intech.digital.subscriptions.core.repository"
    }
)
@EntityScan(
    basePackages = {
        "ru.rshb.intech.digital.subscriptions.entity",
    }
)
@EnableTransactionManagement
public class TestPersistenceConfig {

  @Bean
  @Primary
  public DataSource dataSourceTest() {
    final var hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl("jdbc:postgresql://192.168.0.100:25428/Digital");
    hikariConfig.setUsername("postgres");
    hikariConfig.setPassword("postgres");

    return new HikariDataSource(hikariConfig);
  }

  @Bean
  public EntityManagerFactory entityManagerFactory(SessionFactory sessionFactory) {
    return sessionFactory;
  }

  @Bean
  @Primary
  public SessionFactory sessionFactory(DataSource dataSource) {
    return new LocalSessionFactoryBuilder(dataSource)
        .scanPackages("ru.rshb.intech.digital")
        .buildSessionFactory();
  }

  @Bean
  @Primary
  public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
    return new JpaTransactionManager(sessionFactory);
  }
}
