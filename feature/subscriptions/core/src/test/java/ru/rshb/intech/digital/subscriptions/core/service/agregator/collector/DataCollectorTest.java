package ru.rshb.intech.digital.subscriptions.core.service.agregator.collector;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.rshb.intech.digital.subscriptions.core.service.agregator.pool.DataPool;
import ru.rshb.intech.digital.subscriptions.core.test.CacheConfig;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@SpringBootTest(
    classes = {
        DataCollectorImpl.class
    },
    properties = {
        "external.url=https://rshbdigital.ru"
    }
)
@Import({CacheConfig.class})
class DataCollectorTest {

  @Autowired
  private DataCollector dataCollector;

  @Test
  void collectData() {

    final DataCollectorOptions dataCollectorOptions = DataCollectorOptions.builder()
        .periodStartDate(LocalDate.now().minus(1, ChronoUnit.WEEKS))
        .periodEndDate(LocalDate.now())
        .build();

    final DataPool dataPool = dataCollector.collectData(dataCollectorOptions);

    assertNotNull(dataPool);
    assertFalse(dataPool.isEmpty());
  }
}