package ru.rshb.intech.digital.content.provider.elasticsearch.dto.converter;

import jakarta.annotation.Nonnull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.elasticsearch.core.mapping.PropertyValueConverter;

import java.time.LocalDate;

import static ru.rshb.intech.digital.commons.utils.datetime.DateTimeFormat.ISO_EXTENDED_DATE;

public class NullableLocalDateConverter implements PropertyValueConverter {

  @Nonnull
  @Override
  public Object write(@Nonnull Object value) {
    final var strVal = value.toString();
    if (StringUtils.isNotBlank(strVal)) {
      return LocalDate.parse(strVal, ISO_EXTENDED_DATE.getFormatter());
    }

    return null; // FIXME: contract violation
  }

  @Nonnull
  @Override
  public Object read(@Nonnull Object value) {
    final var strVal = value.toString();
    if (StringUtils.isNotBlank(strVal)) {
      return LocalDate.parse(strVal, ISO_EXTENDED_DATE.getFormatter());
    }
    return null;
  }
}
