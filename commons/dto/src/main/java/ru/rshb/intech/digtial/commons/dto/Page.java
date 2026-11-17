package ru.rshb.intech.digtial.commons.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Generated
public class Page<C> {

  @Schema(description = "Массив записей контента")
  private List<C> content;

  @Schema(
      description = "Количество элементов, доступных для получения всего.",
      example = "21"
  )
  private long totalElements;

  @Schema(
      description = "Количество элементов в текущей странице",
      example = "1"
  )
  private long numberOfElements;

  @Schema(
      description = "Количество элементов на страницу",
      example = "20"
  )
  private long size;

  @Schema(
      description = "Текущая страница. 0-based",
      example = "1"
  )
  private long page;

  @Schema(
      description = "Число доступных страниц.",
      example = "2"
  )
  private long totalPages;
}
