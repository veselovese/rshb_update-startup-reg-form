package ru.rshb.intech.digital.service.controller.publications;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.publications.core.service.publication.PublicationService;
import ru.rshb.intech.digital.publications.dto.LikeDto;
import ru.rshb.intech.digital.publications.dto.PublicationSummaryInfo;
import ru.rshb.intech.digital.publications.entity.Like;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
    name = "Лайки и просмотры. Все разделы.",
    description = "API для работы с лайками и просмотрами публикаций."
)
@RestController
@CrossOrigin
@RequestMapping(
    path = "${api.prefix}/publication",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class PublicationController {

  private final PublicationService publicationService;


  //@formatter:off
  @Operation(
      summary = "Получить сведения о просмотрах и лайках публикаций и увеличить счетчики просмотров.",
      description = "Инкрементирует счетчики просмотров для публикаций с указанными ID для данного пользователя (по сессии).",
      parameters = @Parameter(
          name = "id",
          description = "Один или несколько ID публикаций, по которым требуется выполнить данную операцию."
      ),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешное выполнение операции."
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Предоставлены некорректные данные."
          )
      }
  )
  //@formatter:on
  @GetMapping("/summary")
  public List<PublicationSummaryInfo> getSummary(@RequestParam("id") List<String> publicationIds,
                                                 @Nonnull HttpSession session) {

    return publicationService.getPublicationsSummaries(publicationIds, session.getId(), true);
  }

  //@formatter:off
  @Operation(
      summary = "Поставить/убрать лайк публикации.",
      description = "Изменяет состояние лайка для публикации с указанным ID для данного пользователя.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешное выполнение операции."
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Предоставлены некорректные данные."
          )
      }
  )
  @PutMapping(
      path = "/{id}/like",
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  //@formatter:on
  public LikeDto.Response putLike(@PathVariable("id") String pubId,
                                  @RequestBody LikeDto.Request request,
                                  @Nonnull HttpSession session) {

    return publicationService.setLikeToPublication(
        Like.builder()
            .sessionId(session.getId())
            .publicationId(pubId)
            .state(request.state())
            .updated(LocalDateTime.now())
            .build()
    );
  }
}
