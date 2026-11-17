package ru.rshb.intech.digital.service.controller.publications.infographics;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.publications.core.service.publication.PublicationService;
import ru.rshb.intech.digital.publications.dto.PublicationSummaryInfo;
import ru.rshb.intech.digtial.commons.dto.OperationResultResponse;

import java.util.List;

@Tag(
    name = "Лайки и просмотры. Инфографика",
    description = "API для работы с лайками и просмотрами публикаций."
)
@RestController
@CrossOrigin
@RequestMapping(
    path = "${api.prefix}/publication/infographics",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class InfographicsController {

  private final PublicationService publicationService;

  //@formatter:off
  @Operation(
      summary = "Получить количество лайков и просмотров инфографики",
      description = "Возвращает сведения о количестве лайков и просмотров инфографики с указанным ID для данного пользователя.",
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
  public List<PublicationSummaryInfo> getSummaries(@RequestParam("id") List<String> infographicIds, @Nonnull HttpSession session) {
    return publicationService.getInfographicsSummaries(infographicIds, session.getId());
  }

  //@formatter:off
  @Operation(
      summary = "Инкрементировать счетчик просмотров инфографики.",
      description = "Инкрементирует счетчик просмотров инфографики с указанным ID."
  )
  @PutMapping(
      path = "/views/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  //@formatter:on
  public PublicationSummaryInfo getViews(@RequestParam("id") String infographicId, @Nonnull HttpSession session) {
    return publicationService.updateInfographicViewCount(infographicId, session.getId());
  }
}
