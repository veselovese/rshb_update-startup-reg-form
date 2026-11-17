package ru.rshb.intech.digital.feature.downloads.core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.feature.downloads.core.service.form.FileRequestForm;
import ru.rshb.intech.digital.feature.downloads.dto.FileRequestDto;
import ru.rshb.intech.digital.feature.downloads.entity.FileRequest;
import ru.rshb.intech.digital.subsystem.content.files.entity.FileIndexEntry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PromoDownloadServiceTest {

  @Mock
  private FileRequestForm fileRequestForm;

  private PromoDownloadService promoDownloadService;

  @BeforeEach
  void setUp() {
    promoDownloadService = new PromoDownloadServiceImpl(fileRequestForm);
  }

  @Test
  void givenValid_getPromoFileUrl_thenReturnUrl() {

    final var expectedUrl = "https://rshbdigital.ru/img/some-file.pdf";

    final var file = new FileIndexEntry();
    file.setUrl(expectedUrl);

    final var expectedFormResult = new FileRequest();
    expectedFormResult.setFile(file);

    final FileRequestDto requestDto = new FileRequestDto();

    given(
        fileRequestForm.process(any())
    ).willReturn(expectedFormResult);

    assertThat(promoDownloadService.getPromoFileUrl(requestDto))
        .isNotNull()
        .isEqualTo(expectedUrl);
  }
}