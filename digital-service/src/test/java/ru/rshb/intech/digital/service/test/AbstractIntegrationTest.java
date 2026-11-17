package ru.rshb.intech.digital.service.test;

import jakarta.annotation.Nonnull;
import org.junit.ClassRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;

@ActiveProfiles({"test", "no-mail"})
@TestPropertySource(
    value = {
        "classpath:application-test.yml"
    }
)
@SpringBootTest
@Import(
    value = {
        TestConfig.class
    }
)
@ContextConfiguration(
    initializers = {AbstractIntegrationTest.Initializer.class}
)
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@Testcontainers
public abstract class AbstractIntegrationTest {

  @Container
  @ClassRule
  private static final TestPostgresContainer container = TestPostgresContainer.getInstance();

  @Value("${api.prefix}")
  private String apiPrefix;

  @Autowired
  private MockMvc mockMvc;

  @Nonnull
  protected MockMvc mockMvc() {
    return mockMvc;
  }

  @Nonnull
  protected String endpoint(@Nonnull String varPart) {
    return "/" + apiPrefix + varPart;
  }

  @Nonnull
  protected URI endpointUri(@Nonnull String varPart) {
    final String endpoint = endpoint(varPart);
    return URI.create(endpoint);
  }

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
