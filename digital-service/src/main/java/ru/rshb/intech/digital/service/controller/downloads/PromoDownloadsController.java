package ru.rshb.intech.digital.service.controller.downloads;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feature.downloads.core.service.PromoDownloadService;
import ru.rshb.intech.digital.feature.downloads.dto.FileDownloadResponseDto;
import ru.rshb.intech.digital.feature.downloads.dto.FileRequestDto;
import ru.rshb.intech.digtial.commons.dto.FormValidationErrorResponse;

@Tag(
    name = "Файлы",
    description = "API для скачивания файлов"
)
@RestController
@RequestMapping(path = "${api.external.prefix}/downloads/promo")
@RequiredArgsConstructor
public class PromoDownloadsController {

  private final PromoDownloadService promoDownloadService;

  @Operation(
      summary = "Отправить заявку на скачивание промо-файла",
      description = "Принимает данные пользователя и возвращает ссылку на скачивание файла",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Данные приняты."
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Переданные данные невалидны.",
              content = @Content(
                  schema = @Schema(
                      implementation = FormValidationErrorResponse.class
                  )
              )
          )
      },
      requestBody = @RequestBody(description = "Данные заявки на скачивание файла.")
  )
  @PostMapping(
      path = "/request",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @Transactional
  public FileDownloadResponseDto submitPromoFileDownloadRequest(@Valid @ModelAttribute FileRequestDto requestDto) {
    final var fileUrl = promoDownloadService.getPromoFileUrl(requestDto);
    return new FileDownloadResponseDto(fileUrl);
  }
}
