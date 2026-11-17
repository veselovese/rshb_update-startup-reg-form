package ru.rshb.intech.digital.commons.validation.phone;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneUtilsTest {

  @Test
  void getPhone_normal() {
    String phone = "+7 (999) 666-42-42";
    String normalized = PhoneUtils.getPhone(phone)
        .orElseThrow();
    assertEquals("+79996664242", normalized);
  }

  @Test
  void getPhone_normal_with_8() {
    String phone = "8 (999) 666-42-42";
    String normalized = PhoneUtils.getPhone(phone)
        .orElseThrow();
    assertEquals("89996664242", normalized);
  }

  @Test
  void getPhone_normalized() {
    String phone = "+79996664242";
    String normalized = PhoneUtils.getPhone(phone)
        .orElseThrow();
    assertEquals("+79996664242", normalized);
  }

  @Test
  void getPhone_normalized_with_8() {
    String phone = "89996664242";
    String normalized = PhoneUtils.getPhone(phone)
        .orElseThrow();
    assertEquals("89996664242", normalized);
  }

  @Test
  void getPhone_empty() {
    String phone = "";
    boolean empty = PhoneUtils.getPhone(phone).isEmpty();
    assertTrue(empty);
  }

  @Test
  void getPhone_blank() {
    String phone = "     ";
    boolean empty = PhoneUtils.getPhone(phone).isEmpty();
    assertTrue(empty);
  }

  @Test
  void getPhone_text() {
    String phone = "abracadabra";
    boolean empty = PhoneUtils.getPhone(phone).isEmpty();
    assertTrue(empty);
  }

  @Test
  void getPhone_long() {
    String phone = "899966642424242";
    boolean empty = PhoneUtils.getPhone(phone).isEmpty();
    assertTrue(empty);
  }
}