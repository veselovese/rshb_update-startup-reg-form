package ru.rshb.intech.digital.subscriptions.core.service.agregator.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataItemTest {

  @Test
  void testEquals() {

    final DataItem d1 = new DataItem();
    d1.setTitle("DataItem");

    final DataItem d2 = new DataItem();
    d2.setTitle("DataItem");

    assertEquals(d1, d2);
  }

  @Test
  void canEqual() {

    final DataItem d1 = new DataItem();
    d1.setTitle("DataItem");

    final DataItem d2 = new DataItem();

    assertTrue(d1.canEqual(d2));
  }

  @Test
  void testHashCode() {
    final DataItem d1 = new DataItem();
    final int hcBefore = d1.hashCode();

    d1.setTitle("DataItem");
    final int hcAfter = d1.hashCode();

    assertNotEquals(hcBefore, hcAfter);
  }
}