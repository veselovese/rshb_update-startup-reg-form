package ru.rshb.intech.digital.subscriptions.core.test;

import jakarta.annotation.Nonnull;
import org.junit.ClassRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.rshb.intech.digital.migration.posgres.config.PostgresMigrationConfig;

@ActiveProfiles({"test", "no-mail"})
@TestPropertySource(
    value = {
        "classpath:application-test.yml"
    }
)
@SpringBootTest
@Import(value = {PostgresMigrationConfig.class, TestConfig.class, TestPersistenceConfig.class})
@ContextConfiguration(
    initializers = {AbstractIntegrationTest.Initializer.class}
)
@Testcontainers
public abstract class AbstractIntegrationTest {

  @Container
  @ClassRule
  private static final TestPostgresContainer container = TestPostgresContainer.getInstance();

  public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(@Nonnull ConfigurableApplicationContext applicationContext) {
      TestPropertyValues.of(
          "spring.datasource.url" + container.getJdbcUrl(),
          "spring.datasource.username" + container.getUsername(),
          "spring.datasource.password" + container.getPassword(),
          "spring.liquibase.enabled=true"
      ).applyTo(applicationContext.getEnvironment());
    }
  }
}
