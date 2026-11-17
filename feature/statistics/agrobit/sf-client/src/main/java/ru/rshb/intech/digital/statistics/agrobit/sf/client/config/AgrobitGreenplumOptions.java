package ru.rshb.intech.digital.statistics.agrobit.sf.client.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AgrobitGreenplumOptions {

  @Value("${digital.agrobit.datasource.url}")
  private String url;

  @Value("${digital.agrobit.datasource.username}")
  private String username;

  @Value("${digital.agrobit.datasource.password}")
  private String password;

  @Value("${digital.agrobit.datasource.driver-class-name}")
  private String driverClass;
}
