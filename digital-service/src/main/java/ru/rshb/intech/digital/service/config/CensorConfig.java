package ru.rshb.intech.digital.service.config;

import jakarta.annotation.Nonnull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rshb.intech.digital.censor.api.CensorService;
import ru.rshb.intech.digital.censor.forms.CensorAnalyzer;

@Configuration
public class CensorConfig {

  @Bean
  public CensorAnalyzer censorAnalyzer(@Nonnull CensorService censorService) {
    return new CensorAnalyzer(censorService);
  }

}
