package ru.rshb.intech.digital.feature.contests.patent.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Schema(name = "Город из списка по-умолчанию")
@Data
@Generated
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CityDto extends SimpleLookupValue {

  public CityDto(@Nonnull String id, @Nonnull String value) {
    super(id, value);
  }
}
