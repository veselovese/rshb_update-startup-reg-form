package ru.rshb.intech.digital.service.config.options;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.config.AgrobitGreenplumOptions;

@Getter
@Component
@RequiredArgsConstructor
public class DatabaseOptions {

  private final DigitalDatabaseOptions main;
  private final AgrobitGreenplumOptions agrobit;

  @Getter
  @Component
  public static class DigitalDatabaseOptions {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;
  }
}
