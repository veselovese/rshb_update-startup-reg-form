package ru.rshb.intech.digital.service.controller.publications.podcasts;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.publications.core.service.publication.PublicationService;
import ru.rshb.intech.digital.publications.dto.PublicationSummaryInfo;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

import java.util.List;

@Tag(
    name = "Лайки и просмотры. Подкасты",
    description = "API для работы с лайками и просмотрами публикаций."
)
@RestController
@CrossOrigin
@RequestMapping(
    path = "${api.prefix}/publication/podcast",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class PodcastController {

  private final PublicationService publicationService;


  //@formatter:off
  @Operation(
      summary = "Получить количество лайков и прослушиваний подкаста",
      description = "Возвращает сведения о количестве лайков и прослушиваний подкаста с указанным ID для данного пользователя.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешное выполнение операции."
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Предоставлены некорректные данные.",
              content = @Content(
                  schema = @Schema(
                      implementation = OperationResultResponse.class
                  )
              )
          )
      }
  )
  //@formatter:on
  @GetMapping
  public List<PublicationSummaryInfo> getPodcastSummary(@RequestParam("id") List<String> podcastIds,
                                                        @Nonnull HttpSession session) {

    return publicationService.getPodcastSummaries(podcastIds, session.getId());
  }

  //@formatter:off
  @Operation(
      summary = "Инкрементировать счетчик прослушиваний подкаста.",
      description = "Инкрементирует счетчик прослушиваний подкаста с указанным ID."
  )
  //@formatter:on
  @PutMapping("/listening/{id}")
  public PublicationSummaryInfo putPodcastListening(@PathVariable("id") String publicationId,
                                                    @Nonnull HttpSession session) {

    return publicationService.updatePodcastListeningCount(publicationId, session.getId());
  }
}
