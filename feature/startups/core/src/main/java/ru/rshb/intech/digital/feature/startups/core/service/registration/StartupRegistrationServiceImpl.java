package ru.rshb.intech.digital.feature.startups.core.service.registration;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.startups.core.repository.StartupRegistrationRepository;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class StartupRegistrationServiceImpl implements StartupRegistrationService {

  private static final String[] ALLOWED_LOGO_EXTENSIONS = {"svg", "eps", "png"};
  private static final String[] ALLOWED_PRESENTATION_EXTENSIONS = {"ppt", "pptx", "doc", "docx", "pdf"};

  private final StartupRegistrationRepository registrationRepository;

  @Nonnull
  @Override
  @Transactional
  public StartupRegistration save(@Nonnull StartupRegistration registration) {
    registration.setSubmitDate(LocalDateTime.now());
    final var savedReg = registrationRepository.save(registration);
    log.info("New startup registration has been saved");
    return savedReg;
  }

  @Nonnull
  @Override
  public String[] getLogoAllowedExtensions() {
    return ALLOWED_LOGO_EXTENSIONS;
  }

  @Nonnull
  @Override
  public String[] getPresentationAllowedExtensions() {
    return ALLOWED_PRESENTATION_EXTENSIONS;
  }
}
