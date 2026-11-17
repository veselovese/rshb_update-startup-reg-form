package ru.rshb.intech.digital.vacancies.core.repository.candidate;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rshb.intech.digital.vacancies.entity.CandidateCv;

public interface VacancyCandidateRepository extends JpaRepository<CandidateCv, Long> {

}
