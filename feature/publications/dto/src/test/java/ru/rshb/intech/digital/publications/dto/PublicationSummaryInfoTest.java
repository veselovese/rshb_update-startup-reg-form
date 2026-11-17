package ru.rshb.intech.digital.publications.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PublicationSummaryInfoTest {

  @Test
  void empty() {
    final var pubId = "123";
    final PublicationSummaryInfo summary = PublicationSummaryInfo.empty(pubId);
    assertNotNull(summary);
    assertEquals(pubId, summary.getPublicationId());
    assertEquals(0L, summary.getLikes());
    assertEquals(0L, summary.getViews());
    assertEquals(LikeState.UNLIKED, summary.getState());
  }
}