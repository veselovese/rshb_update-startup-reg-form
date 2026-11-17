package ru.rshb.intech.digital.publications.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Generated;

public class LikeDto {

  @Schema(name = "Данные для изменения состояния лайка")
  @Generated
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public record Request(

      @Schema(description = "ID публикации.")
      @NotBlank(message = "{publications.like.publication.id.missing}")
      String publicationId,

      @Schema(description = "Устанавливаемое состояние лайка.")
      @NotBlank(message = "{publications.like.state.missing}")
      LikeState state
  ) {

  }

  @Schema(name = "Результат изменения состояния лайка")
  @Generated
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public record Response(

      @Schema(description = "Актуальное состояние счетчика лайков.")
      Long count,

      @Schema(description = "Актуальное состояние лайка.")
      LikeState state
  ) {

  }
}
