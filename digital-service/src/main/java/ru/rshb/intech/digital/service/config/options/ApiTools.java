package ru.rshb.intech.digital.service.config.options;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApiTools {

  private final String prefix;

  public ApiTools(@Value("${api.prefix}") String prefix) {
    this.prefix = prefix;
  }

  @Nonnull
  public String endpoint(@Nonnull String relativePath) {
    return "/" + prefix + relativePath;
  }
}
