package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadRegistration;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.repository.AgrotechOlympiadRegistrationRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AgrotechOlympiadRegistrationServiceImpl implements AgrotechOlympiadRegistrationService {

  private static final long MAX_REG_NUMBER_PER_EMAIL = 2;

  private final AgrotechOlympiadRegistrationRepository registrationRepository;


  @Nonnull
  @Override
  @Transactional
  public AgrotechOlympiadRegistration save(@Nonnull AgrotechOlympiadRegistration registration) {
    registration.setSubmitDate(LocalDateTime.now());
    final var savedEntry = registrationRepository.save(registration);
    log.info("New Agrotech Olympiad registration has been saved");
    return savedEntry;
  }

  @Override
  public boolean isRegistrationAllowed(@Nonnull String email) {
    final var regsNum = registrationRepository.countByEmailIgnoreCase(email);
    return (regsNum < MAX_REG_NUMBER_PER_EMAIL);
  }
}
