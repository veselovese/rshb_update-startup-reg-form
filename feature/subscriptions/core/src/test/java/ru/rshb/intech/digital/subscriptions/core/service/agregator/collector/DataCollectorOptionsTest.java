package ru.rshb.intech.digital.subscriptions.core.service.agregator.collector;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class DataCollectorOptionsTest {

  @Test
  void getPeriodStartDate() {
    final DataCollectorOptions options = new DataCollectorOptions();
    assertNull(options.getPeriodStartDate());
  }

  @Test
  void getPeriodEndDate() {
    final DataCollectorOptions options = new DataCollectorOptions();
    assertNull(options.getPeriodEndDate());
  }

  @Test
  void setPeriodStartDate() {
    final DataCollectorOptions options = new DataCollectorOptions();
    options.setPeriodStartDate(LocalDate.now());
    assertNotNull(options.getPeriodStartDate());
  }

  @Test
  void setPeriodEndDate() {
    final DataCollectorOptions options = new DataCollectorOptions();
    options.setPeriodEndDate(LocalDate.now());
    assertNotNull(options.getPeriodEndDate());
  }

  @Test
  void builder() {
    final var builder = DataCollectorOptions.builder();
    assertNotNull(builder);
  }
}