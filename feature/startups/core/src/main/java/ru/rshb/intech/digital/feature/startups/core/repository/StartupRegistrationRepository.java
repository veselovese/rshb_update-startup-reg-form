package ru.rshb.intech.digital.feature.startups.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.startups.entity.StartupRegistration;

public interface StartupRegistrationRepository extends JpaRepository<StartupRegistration, Long> {

}
