package ru.rshb.intech.digital.subscriptions.core.test;

import jakarta.annotation.Nonnull;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class TestPostgresContainer extends PostgreSQLContainer<TestPostgresContainer> {

  public static final String IMAGE_VERSION = "postgres:12.7";

  private static TestPostgresContainer container;

  private TestPostgresContainer() {
    super(IMAGE_VERSION);
    addFixedExposedPort(25428, 5432);

    withInitScript("TcInitScript.sql")
        .withDatabaseName("Digital")
        .withUsername("postgres")
        .withPassword("postgres")
        .withEnv("POSTGRES_USER", "postgres")
        .withEnv("POSTGRES_PASSWORD", "postgres")
        .withEnv("POSTGRES_DB", "Digital")
        .waitingFor(Wait.forListeningPort());
  }

  @Nonnull
  public static TestPostgresContainer getInstance() {
    if (container == null) {
      container = new TestPostgresContainer();
    }
    return container;
  }

  @Override
  public void stop() {
  }
}
