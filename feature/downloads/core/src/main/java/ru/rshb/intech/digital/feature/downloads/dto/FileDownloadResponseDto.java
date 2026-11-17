package ru.rshb.intech.digital.feature.downloads.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Schema(name = "Ссылка для скачивания файла")
@Data
@Generated
@NoArgsConstructor
public class FileDownloadResponseDto {

  @Schema(
      description = "Ссылка на скачивание файла",
      example = "https://rshbdigital.ru/files/0000001.pdf"
  )
  private String fileUrl;

  public FileDownloadResponseDto(@Nonnull String fileUrl) {
    this.fileUrl = fileUrl;
  }

}
