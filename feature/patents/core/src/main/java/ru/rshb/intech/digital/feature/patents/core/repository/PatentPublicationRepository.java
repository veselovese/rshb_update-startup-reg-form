package ru.rshb.intech.digital.feature.patents.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.feature.patents.core.entity.PatentPublication;

public interface PatentPublicationRepository extends JpaRepository<PatentPublication, Long> {

}
