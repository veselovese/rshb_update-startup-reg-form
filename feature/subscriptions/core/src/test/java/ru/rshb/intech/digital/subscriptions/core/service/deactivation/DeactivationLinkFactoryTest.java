package ru.rshb.intech.digital.subscriptions.core.service.deactivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@SpringBootTest(
    classes = UuidDeactivationCodeFactory.class
)
class DeactivationLinkFactoryTest {

  public static final String HOST = "http://localhost:8080";

  @Autowired
  private DeactivationCodeFactory deactivationCodeFactory;

  private DeactivationLinkFactory deactivationLinkFactory;

  @BeforeEach
  void setUp() {
    this.deactivationLinkFactory = new DefaultDeactivationLinkFactory(HOST);
  }

  @Test
  void createNewLink() {

    final String deactivationCode = deactivationCodeFactory.createNewCode();
    final String url = deactivationLinkFactory.createNewLink(deactivationCode);

    assertNotNull(url);
    assertDoesNotThrow(() -> {
      URI.create(url).toURL();
    });
  }
}