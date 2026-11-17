package ru.rshb.intech.digtial.commons.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PageTest {

  @Test
  void builder() {
    final var page = Page.builder()
        .page(1L)
        .totalPages(10L)
        .content(List.of())
        .numberOfElements(10L)
        .totalElements(100L)
        .size(1)
        .build();

    assertThat(page).isNotNull();
  }

  @Test
  void empty_builder() {
    final var builder = Page.builder();
    assertThat(builder).isNotNull();
    assertThat(builder.toString()).isNotBlank();
  }
}