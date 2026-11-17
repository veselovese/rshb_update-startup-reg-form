package ru.rshb.intech.digital.subscriptions.core.service.activation;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActivationInfoTest {

  @Test
  void getActivationUrl() {
    final ActivationInfo activationInfo = new ActivationInfo();
    assertNull(activationInfo.getActivationUrl());
  }

  @Test
  void setActivationUrl() {
    final ActivationInfo activationInfo = new ActivationInfo();
    activationInfo.setActivationUrl("http://127.0.0.1");
    assertTrue(StringUtils.isNotBlank(activationInfo.getActivationUrl()));
  }
}