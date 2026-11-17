package ru.rshb.intech.digital.service.controller.publications.innovations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    name = "Лайки и просмотры. Инновации",
    description = "API для работы с лайками и просмотрами публикаций."
)
@RestController
@CrossOrigin
@RequestMapping(
    path = "${api.prefix}/publication/innovation",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class InnovationPublicationController {

  private final PublicationService publicationService;


  //@formatter:off
  @Operation(
      summary = "Получить количество лайков и просмотров карточек заявок на инновации",
      description = "Возвращает сведения о количестве лайков и просмотров карточек заявок на инновации с указанным ID для данного пользователя.",
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
      },
      parameters = @Parameter(
          name = "id",
          description = "Один или несколько ID карточек заявок на инновации"
      )
  )
  //@formatter:on
  @GetMapping("/demand")
  public List<PublicationSummaryInfo> getInnovationDemandSummary(@RequestParam("id") List<String> demandIds,
                                                                 @Nonnull HttpSession session) {

    return publicationService.getInnovationDemandSummaries(demandIds, session.getId());
  }

  //@formatter:off
  @Operation(
      summary = "Инкрементировать счетчик просмотров карточки заявки на инновации.",
      description = "Инкрементирует счетчик просмотров карточки заявки на инновации."
  )
  //@formatter:on
  @PutMapping("/demand/{id}")
  public PublicationSummaryInfo putInnovationDemandView(@PathVariable("id") String demandId,
                                                        @Nonnull HttpSession session) {

    return publicationService.updateInnovationDemandViewCount(demandId, session.getId());
  }
}
