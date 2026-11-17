package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadRegistration;

public interface AgrotechOlympiadRegistrationRepository extends JpaRepository<AgrotechOlympiadRegistration, Long> {

  long countByEmailIgnoreCase(String email);
}
