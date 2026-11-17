package ru.rshb.intech.digital.feature.downloads.core.service.form.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmailTest {

  private Email emailAttribute;

  @BeforeEach
  void setUp() {
    emailAttribute = new Email();
  }

  @Test
  void processOrder() {
    assertThat(emailAttribute.processOrder())
        .isEqualTo(3);
  }

}