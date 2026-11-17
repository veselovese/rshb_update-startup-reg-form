package ru.rshb.intech.digital.feature.contests.patent.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.contests.patent.entity.PatentContestRegistration;
import ru.rshb.intech.digital.feature.contests.patent.repository.PatentContestRegistrationRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatentContestRegistrationServiceImpl implements PatentContestRegistrationService {

  private static final String[] LOGO_EXTENSIONS = {"svg", "eps", "png"};
  private static final String[] PRESENTATION_EXTENSIONS = {"ppt", "pptx", "doc", "docx", "pdf"};


  private final PatentContestRegistrationRepository registrationRepository;

  @Nonnull
  @Override
  @Transactional
  public PatentContestRegistration save(@Nonnull PatentContestRegistration registration) {

    registration.setSubmitDate(LocalDateTime.now());

    final var savedReg = registrationRepository.save(registration);
    log.info("New patent contest registration has been saved");

    return savedReg;
  }

  @Override
  public long getRegistrationCountByEmail(@Nonnull String email) {
    return registrationRepository.countAllByEmailIsIgnoreCase(email);
  }

  @Nonnull
  @Override
  public String[] getLogoSupportedExtensions() {
    return LOGO_EXTENSIONS;
  }

  @Nonnull
  @Override
  public String[] getPresentationSupportedExtensions() {
    return PRESENTATION_EXTENSIONS;
  }
}
