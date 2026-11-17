package ru.rshb.intech.digital.feature.patents.core.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;
import ru.rshb.intech.digital.feature.patents.core.repository.PatentPublicationRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatentPublicationServiceImpl implements PatentPublicationService {

  private static final String[] LOGO_ALLOWED_EXTENSIONS = {"svg", "eps", "png"};
  private static final String[] PRESENTATION_ALLOWED_EXTENSIONS = {"ppt", "pptx", "doc", "docx", "pdf"};


  private final PatentPublicationRepository patentPublicationRepository;

  @Nonnull
  @Override
  @Transactional
  public PatentPublication save(@Nonnull PatentPublication publication) {
    publication.setSubmitDate(LocalDateTime.now());
    final var savedPub = patentPublicationRepository.save(publication);
    log.info("New patent publication request saved");
    return savedPub;
  }

  @Nonnull
  @Override
  public String[] getLogoAllowedExtensions() {
    return LOGO_ALLOWED_EXTENSIONS;
  }

  @Nonnull
  @Override
  public String[] getPresentationAllowedExtensions() {
    return PRESENTATION_ALLOWED_EXTENSIONS;
  }
}
