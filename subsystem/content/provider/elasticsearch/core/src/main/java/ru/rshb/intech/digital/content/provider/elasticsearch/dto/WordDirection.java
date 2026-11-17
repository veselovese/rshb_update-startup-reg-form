package ru.rshb.intech.digital.content.provider.elasticsearch.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Nonnull;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Generated
@RequiredArgsConstructor
public enum WordDirection {

  ROW("row"),

  COLUMN("column");

  private final String key;

  @JsonValue
  public String getKey() {
    return key;
  }

  @Nonnull
  public static WordDirection parse(String value) {
    return Stream.of(values())
        .filter(item -> item.getKey().equalsIgnoreCase(value))
        .findAny()
        .orElseThrow(() -> new UnsupportedOperationException("Unknown word direction type: " + value));
  }
}
