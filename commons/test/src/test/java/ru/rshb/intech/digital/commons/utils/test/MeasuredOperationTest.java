package ru.rshb.intech.digital.commons.utils.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MeasuredOperationTest {

  @Test
  @SneakyThrows
  void run_procedure() {
    MeasuredOperation.run("run_procedure", () -> {
    });
  }

  @Test
  @SneakyThrows
  void run_function() {
    Integer result = MeasuredOperation.run("run_function", () -> 1);
    assertNotNull(result);
    assertEquals(1, result);
  }
}