package ru.rshb.intech.digital.feature.downloads.core.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.downloads.core.service.form.FileRequestForm;
import ru.rshb.intech.digital.feature.downloads.dto.FileRequestDto;

@Service
@RequiredArgsConstructor
public class PromoDownloadServiceImpl implements PromoDownloadService {

  private final FileRequestForm fileRequestForm;

  @Nonnull
  @Override
  public String getPromoFileUrl(@Nonnull FileRequestDto fileRequestDto) {
    final var fileRequest = fileRequestForm.process(fileRequestDto);
    return fileRequest.getFile().getUrl();
  }
}
