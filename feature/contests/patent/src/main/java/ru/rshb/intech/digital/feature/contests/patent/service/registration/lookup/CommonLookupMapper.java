package ru.rshb.intech.digital.feature.contests.patent.service.registration.lookup;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.feature.contests.patent.dto.SimpleLookupValue;
import ru.rshb.intech.digital.feature.contests.patent.entity.AbstractLookupEntry;

import java.util.List;
import java.util.function.Supplier;

@Component
public class CommonLookupMapper {

  @Nonnull
  public <E extends AbstractLookupEntry, D extends SimpleLookupValue> D map(@Nonnull E entity,
                                                                            @Nonnull Supplier<D> dtoSupplier) {
    final var dto = dtoSupplier.get();
    dto.setId(entity.getExternalId());
    dto.setValue(entity.getValue());
    return dto;
  }

  @Nonnull
  public <E extends AbstractLookupEntry, D extends SimpleLookupValue> List<D> map(@Nonnull List<E> entities,
                                                                                  @Nonnull Supplier<D> dtoSupplier) {
    return entities.stream()
        .map(entity -> map(entity, dtoSupplier))
        .toList();
  }

}
