package ru.rshb.intech.digital.feature.blogs.core.experts.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertRegistration;
import ru.rshb.intech.digital.feature.blogs.core.experts.repository.ExpertRegistrationRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpertRegistrationServiceImpl implements ExpertRegistrationService {

  private final ExpertRegistrationRepository registrationRepository;

  @Nonnull
  @Override
  @Transactional
  public ExpertRegistration save(@Nonnull ExpertRegistration registration) {
    registration.setSubmitDate(LocalDateTime.now());
    final var savedReg = registrationRepository.save(registration);
    log.info("New expert registration has been saved");
    return savedReg;
  }
}
