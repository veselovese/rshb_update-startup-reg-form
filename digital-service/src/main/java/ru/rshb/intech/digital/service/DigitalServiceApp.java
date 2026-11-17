package ru.rshb.intech.digital.service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@OpenAPIDefinition(
    info = @Info(
        title = "RSHB Digital Service API",
        version = "1.0"
    )
)
@EnableWebMvc
@SpringBootApplication
public class DigitalServiceApp {

  public static void main(String[] args) {
    Thread.setDefaultUncaughtExceptionHandler(new AppExceptionHandler());
    SpringApplication.run(DigitalServiceApp.class, args);
  }

  @Slf4j
  private static class AppExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
      log.error("Unexpected error", e);
    }
  }
}
