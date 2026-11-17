package ru.rshb.intech.digital.publications.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Schema(name = "Сведения о лайках и просмотрах публикации")
@Data
@Builder
@Generated
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublicationSummaryInfo {

  private String publicationId;
  private Long views;
  private Long likes;
  private LikeState state;

  @Nonnull
  public static PublicationSummaryInfo empty(@Nonnull String publicationId) {
    return PublicationSummaryInfo.builder()
        .publicationId(publicationId)
        .likes(0L)
        .views(0L)
        .state(LikeState.UNLIKED)
        .build();
  }
}
