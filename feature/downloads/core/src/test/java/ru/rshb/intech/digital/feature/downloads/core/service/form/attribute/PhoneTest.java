package ru.rshb.intech.digital.feature.downloads.core.service.form.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneTest {

  private Phone phoneAttribute;

  @BeforeEach
  void setUp() {
    phoneAttribute = new Phone();
  }

  @Test
  void processOrder() {
    assertThat(phoneAttribute.processOrder())
        .isEqualTo(2);
  }
}