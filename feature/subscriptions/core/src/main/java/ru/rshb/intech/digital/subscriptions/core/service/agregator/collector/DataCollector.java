package ru.rshb.intech.digital.subscriptions.core.service.agregator.collector;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.pool.DataPool;

public interface DataCollector {

  @Nonnull
  DataPool collectData(@Nonnull DataCollectorOptions options);
}
