package ru.rshb.intech.digital.feature.downloads.core.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.downloads.dto.FileRequestDto;

public interface PromoDownloadService {

  @Nonnull
  String getPromoFileUrl(@Nonnull FileRequestDto fileRequestDto);

}
