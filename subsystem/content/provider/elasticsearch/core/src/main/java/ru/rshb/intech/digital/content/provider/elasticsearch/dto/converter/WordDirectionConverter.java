package ru.rshb.intech.digital.content.provider.elasticsearch.dto.converter;

import org.springframework.data.elasticsearch.core.mapping.PropertyValueConverter;
import ru.rshb.intech.digital.content.provider.elasticsearch.dto.WordDirection;

public class WordDirectionConverter implements PropertyValueConverter {

  @Override
  public Object write(Object value) {
    if (value instanceof WordDirection) {
      return ((WordDirection) value).getKey();
    }
    return null;
  }

  @Override
  public Object read(Object value) {
    if (value instanceof String) {
      final var str = (String) value;
      return WordDirection.parse(str);
    }
    throw new UnsupportedOperationException("Unknown word direction: " + value);
  }
}
