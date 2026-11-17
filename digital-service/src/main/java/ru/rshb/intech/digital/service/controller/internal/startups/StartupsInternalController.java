package ru.rshb.intech.digital.service.controller.internal.startups;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rshb.intech.digital.feature.startups.core.service.importing.StartupConverterService;
import ru.rshb.intech.digital.feature.startups.dto.StartupConvertDto;

/**
 * Контроллер для обработки данных формы конвертера карточек компаний/стартапов.
 */
@Hidden
@RestController
@RequestMapping(
    path = "${api.internal.prefix}/startups"
)
@RequiredArgsConstructor
@Profile({"dev-local", "dev-stand"})
public class StartupsInternalController {

  private final StartupConverterService startupConverterService;

  /**
   * Обрабатывает запрос на конвертирование данных компаний/стартапов из
   * формата XLSX в архив с набором файлов в формате JSON.
   *
   * @param startupConvertDto Данные формы конвертера.
   * @param httpSession HTTP сессия.
   * @return Байтовый массив с архивом из JSON-файлов.
   */
  @PostMapping(
      path = "/convert",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
      produces = "application/zip"
  )
  public ResponseEntity<ByteArrayResource> postConvertStartups(@ModelAttribute @Valid StartupConvertDto startupConvertDto,
                                                               HttpSession httpSession) {

    final var export = startupConverterService.convert(httpSession.getId(), startupConvertDto);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + export.fileName())
        .contentLength(export.fileData().length)
        .body(new ByteArrayResource(export.fileData()));

  }
}
